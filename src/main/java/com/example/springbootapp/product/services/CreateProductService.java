package com.example.springbootapp.product.services;

import com.example.springbootapp.Command;
import com.example.springbootapp.product.ProductRepository;
import com.example.springbootapp.product.model.Product;
import com.example.springbootapp.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {
    private static final Logger logger = LoggerFactory.getLogger(GetProductService.class);
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {

        logger.info("Executing " + getClass());

        Product savedProduct = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }
}
