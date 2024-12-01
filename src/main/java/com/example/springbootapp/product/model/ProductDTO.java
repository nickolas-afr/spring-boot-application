package com.example.springbootapp.product.model;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String name, description;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
    }
}
