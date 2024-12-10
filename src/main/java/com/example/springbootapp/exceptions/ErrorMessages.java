package com.example.springbootapp.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found."),
    ACCOUNT_NOT_FOUND("Account not found."),
    NOT_ENOUGH_MONEY("Not enough money."),
    CUSTOMER_NOT_FOUND("Customer not found.");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
