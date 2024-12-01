package com.example.springbootapp.product.services;

import com.example.springbootapp.Command;
import com.example.springbootapp.exceptions.ProductNotFoundException;
import com.example.springbootapp.product.ProductRepository;
import com.example.springbootapp.product.model.Product;
import com.example.springbootapp.product.model.ProductDTO;
import com.example.springbootapp.product.model.UpdateProductCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private static final Logger logger = LoggerFactory.getLogger(GetProductService.class);
    private ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @CachePut(value = "productCache", key = "#input.getId()")
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand input) {

        logger.info("Executing " + getClass() + "for id: " + input);

        Optional<Product> productOptional = productRepository.findById(input.getId());
        if(productOptional.isPresent()){
            Product product = input.getProduct();
            product.setId(input.getId());

            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
        }

        throw new ProductNotFoundException();
    }
}
