package com.example.springbootapp;

import com.example.springbootapp.exceptions.GlobalExceptionHandler;
import com.example.springbootapp.exceptions.ProductNotFoundException;
import com.example.springbootapp.product.ProductRepository;
import com.example.springbootapp.product.model.ErrorResponse;
import com.example.springbootapp.product.model.Product;
import com.example.springbootapp.product.model.ProductDTO;
import com.example.springbootapp.product.services.CreateProductService;
import com.example.springbootapp.product.services.GetProductService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CreateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_is_valid_when_create_product_service_return_product_dto(){
        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setDescription("Product description is at least 20 chars long");
        product.setPrice(9.99);

        when(productRepository.save(product)).thenReturn(product);

        ResponseEntity<ProductDTO> response = createProductService.execute(product);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(product)), response);

        verify(productRepository, times(1)).save(product);
    }

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void given_product_is_not_valid_when_create_product_service_throw_validation_exception(){
        Product product = new Product();
        product.setId(1);
        product.setName(null);
        product.setDescription("<20chars");
        product.setPrice(-9.99);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty());
    }
}
