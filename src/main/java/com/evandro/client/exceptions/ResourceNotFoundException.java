package com.evandro.client.exceptions;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
