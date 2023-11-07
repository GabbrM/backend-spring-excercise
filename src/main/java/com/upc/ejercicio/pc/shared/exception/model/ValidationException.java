package com.upc.ejercicio.pc.shared.exception.model;

public class ValidationException extends RuntimeException{

    public ValidationException(){
        super();
    }

    public ValidationException(String message){
        super(message);
    }
}
