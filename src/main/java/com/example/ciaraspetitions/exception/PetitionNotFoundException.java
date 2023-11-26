package com.example.ciaraspetitions.exception;


public class PetitionNotFoundException extends RuntimeException {

    public PetitionNotFoundException(String message) {
        super(message);
    }
}
