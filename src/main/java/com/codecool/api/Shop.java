package com.codecool.api;

import java.util.List;

public interface Shop {
    
    public String getName();
    public String getOwner();
    public boolean isOpen();
    public void open();
    public void close();
    public List<Product> getProducts();
    public Product findByName(String name);
    public float getPrice(long barcode);
    public boolean hasProduct(long barcode);
    public void addNewProduct(Product product, int quantity, float price);
    public void addProduct(long barcode,int quantity);
    public Product byProduct(long barcode);
    public List<Product> buyProducts(long barcode, int quantity);
    public String toString();
}
