package com.codecool.api;

public class ProductAlreadyExistsException extends ShopException {
    
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
