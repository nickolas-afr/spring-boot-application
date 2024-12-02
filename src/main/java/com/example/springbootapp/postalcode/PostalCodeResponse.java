package com.example.springbootapp.postalcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PostalCodeResponse {
    @JsonProperty("post code")
    private String postCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("places")
    private List<Place> places;

    @Data
    public static class Place {
        @JsonProperty("place name")
        private String placeName;

        @JsonProperty("state")
        private String state;
    }
}
