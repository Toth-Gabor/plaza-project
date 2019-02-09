package com.codecool.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {
    
    private String name;
    private String owner;
    private boolean shopStatus;
    private Map<Long, ShopImpl.ShopEntryImpl> products = new HashMap<>();
    
    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }
    
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
        private Product product;
        private int quantity;
        private float price;
    
        private ShopEntryImpl(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }
    
        public Product getProduct() {
            return this.product;
        }
    
        public void setProduct(Product product) {
            this.product = product;
        }
    
        public int getQuantity() {
            return this.quantity;
        }
    
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    
        public void increaseQuantity(int amount){
            this.quantity += amount;
        }
        public void decreaseQuantity(int amount){
            this.quantity -= amount;
        }
    
        public float getPrice() {
            return this.price;
        }
    
        public void setPrice(float price) {
            this.price = price;
        }
    
        @Override
        public String toString() {
            return "ShopEntryImpl{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
        }
    }
}
