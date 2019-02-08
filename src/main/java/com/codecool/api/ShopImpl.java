package com.codecool.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {
    
    private String name;
    private String owner;
    private boolean shopStatus;
    private Map<Long, ShopEntryImpl> products = new HashMap<>();
    
    
    @Override
    public String getName() {
        return null;
    }
    
    @Override
    public String getOwner() {
        return null;
    }
    
    @Override
    public boolean isOpen() {
        return false;
    }
    
    @Override
    public void open() {
    
    }
    
    @Override
    public void close() {
    
    }
    
    @Override
    public List<Product> getProducts() {
        return null;
    }
    
    @Override
    public Product findByName(String name) {
        return null;
    }
    
    @Override
    public float getPrice(long barcode) {
        return 0;
    }
    
    @Override
    public boolean hasProduct(long barcode) {
        return false;
    }
    
    @Override
    public void addNewProduct(Product product, int quantity, float price) {
    
    }
    
    @Override
    public void addProduct(long barcode, int quantity) {
    
    }
    
    @Override
    public Product byProduct(long barcode) {
        return null;
    }
    
    @Override
    public List<Product> buyProducts(long barcode, int quantity) {
        return null;
    }
    private class ShopEntryImpl{
    
    }
}
