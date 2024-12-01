package com.example.springbootapp.catfact;

import com.example.springbootapp.Query;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {
    private final RestTemplate restTemplate;

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {

        CatFactResponse response = restTemplate.getForObject("https://catfact.ninja/fact?max_length=" + input, CatFactResponse.class);

        CatFactDTO catFactDTO = new CatFactDTO(response.getFact());
        return ResponseEntity.ok(catFactDTO);
    }
}
