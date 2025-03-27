package com.mega.service.account.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransactionType extends RuntimeException {
    public InvalidTransactionType(String message) {
        super(message);
    }

    public InvalidTransactionType(String message, Throwable cause) {
        super(message, cause);
    }
}
