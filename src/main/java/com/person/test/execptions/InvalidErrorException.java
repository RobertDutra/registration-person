package com.person.test.execptions;

public class InvalidErrorException extends Exception{

    private String message;

    public InvalidErrorException(String message) {
        super(message);
        this.message = message;
    }
}
