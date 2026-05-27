package com.spotter_proyect.spotter.core.exceptions.errors;

public class UnauthorizedActionException extends RuntimeException{

    public UnauthorizedActionException (String msg){
        super(msg);
    }
}
