package com.example.springbootapp.product.services;

import com.example.springbootapp.Query;
import com.example.springbootapp.product.ProductRepository;
import com.example.springbootapp.product.model.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>> {

    private static final Logger logger = LoggerFactory.getLogger(GetProductService.class);
    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {

        logger.info("Executing " + getClass() + " for product name: " + name);

        return ResponseEntity.ok(productRepository.findByNameContaining(name)
                .stream().map(ProductDTO::new).toList());
    }
}
