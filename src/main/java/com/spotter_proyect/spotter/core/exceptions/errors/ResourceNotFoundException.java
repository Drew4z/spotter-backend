package com.spotter_proyect.spotter.core.exceptions.errors;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
