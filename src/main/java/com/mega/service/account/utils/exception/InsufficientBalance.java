package com.mega.service.account.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientBalance extends RuntimeException {
    public InsufficientBalance(String message) {
        super(message);
    }

    public InsufficientBalance(String message, Throwable cause) {
        super(message, cause);
    }
}
