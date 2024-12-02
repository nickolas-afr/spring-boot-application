package com.example.springbootapp.postalcode;

import com.example.springbootapp.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class PostalCodeService implements Query<String, PostalCodeDTO> {
    private final RestTemplate restTemplate;

    public PostalCodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<PostalCodeDTO> execute(String postalCode) {
        String url = "https://api.zippopotam.us";
        URI uri = UriComponentsBuilder
                .fromUriString(url + "/us/" + postalCode)
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<PostalCodeResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity, PostalCodeResponse.class);

            PostalCodeResponse responseBody = response.getBody();
            List<PostalCodeResponse.Place> places = responseBody.getPlaces();

            if (places != null && !places.isEmpty()) {
                PostalCodeResponse.Place firstPlace = places.get(0);
                PostalCodeDTO postalCodeDTO = new PostalCodeDTO(
                        firstPlace.getPlaceName(),
                        firstPlace.getState(),
                        responseBody.getCountry()
                );
                return ResponseEntity.ok(postalCodeDTO);
            } else {
                throw new RuntimeException("No places found for the given postal code.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Postal code API is unavailable or postal code not found.");
        }
    }
}
