package com.sagarandcompany.transactiondemo;

public class InvalidOutputException extends RuntimeException {

    public InvalidOutputException(String message) {
        super(message);
    }
}
