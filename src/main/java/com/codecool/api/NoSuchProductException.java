package com.codecool.api;

public class NoSuchProductException extends ShopException {
    
    public NoSuchProductException(String message) {
        super(message);
    }
}
