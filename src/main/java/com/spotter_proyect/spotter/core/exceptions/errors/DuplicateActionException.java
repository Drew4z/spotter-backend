package com.spotter_proyect.spotter.core.exceptions.errors;

public class DuplicateActionException extends RuntimeException{
    public DuplicateActionException (String msg){
        super(msg);
    }
}
