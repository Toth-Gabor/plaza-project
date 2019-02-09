package com.codecool.api;

public class OutOfStockException extends ShopException {
    
    public OutOfStockException(String message) {
        super(message);
    }
}
