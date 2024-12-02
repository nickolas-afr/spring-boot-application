package com.example.springbootapp.postalcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class PostalCodeDTO {
    private String placeName;
    private String state;
    private String country;
}
