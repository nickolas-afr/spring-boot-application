package com.example.springbootapp.product.services;

import com.example.springbootapp.Command;
import com.example.springbootapp.exceptions.ProductNotFoundException;
import com.example.springbootapp.product.ProductRepository;
import com.example.springbootapp.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Integer, Void> {

    private static final Logger logger = LoggerFactory.getLogger(GetProductService.class);
    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer input) {

        logger.info("Executing " + getClass() + " input: " + input);

        Optional<Product> productOptional = productRepository.findById(input);
        if(productOptional.isPresent()){
            productRepository.deleteById(input);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ProductNotFoundException();
    }
}
