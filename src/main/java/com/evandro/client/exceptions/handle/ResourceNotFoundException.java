package com.evandro.client.exceptions.handle;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
