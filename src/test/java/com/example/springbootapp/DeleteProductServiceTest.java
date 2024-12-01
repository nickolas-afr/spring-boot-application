package com.example.springbootapp;

import com.example.springbootapp.exceptions.ProductNotFoundException;
import com.example.springbootapp.product.ProductRepository;
import com.example.springbootapp.product.model.Product;
import com.example.springbootapp.product.model.ProductDTO;
import com.example.springbootapp.product.services.DeleteProductService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_id_when_delete_product_returns_NO_CONTENT(){
        Product product = new Product();
        product.setId(1);
        product.setName("Product name");
        product.setDescription("Product description is at least 20 chars long");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ResponseEntity<Void> response = deleteProductService.execute(1);

        assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT).build(), response);

        verify(productRepository, times(1)).findById(1);
    }

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void given_invalid_id_when_delete_product_throws_product_not_found_exception(){
        Integer invalidProductId = 999;

        when(productRepository.findById(invalidProductId)).thenReturn(Optional.empty());

        //ResponseEntity<Void> response = deleteProductService.execute(2);

        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(invalidProductId));

        verify(productRepository, times(1)).findById(invalidProductId);
        verify(productRepository, times(0)).deleteById(any());
    }
}
