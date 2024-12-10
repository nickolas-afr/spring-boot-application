package com.example.springbootapp.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotEnoughMoneyException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(NotEnoughMoneyException.class);

    public NotEnoughMoneyException() {

        super(ErrorMessages.NOT_ENOUGH_MONEY.getMessage());
        logger.error("Exception " + getClass() + " thrown.");
    }
}