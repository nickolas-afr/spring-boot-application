package com.example.springbootapp.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(AccountNotFoundException.class);

    public AccountNotFoundException() {

        super(ErrorMessages.ACCOUNT_NOT_FOUND.getMessage());
        logger.error("Exception " + getClass() + " thrown.");
    }
}