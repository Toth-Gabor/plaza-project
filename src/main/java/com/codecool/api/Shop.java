package com.codecool.api;

import java.util.List;

public interface Shop {
    
    public String getName();
    public String getOwner();
    public boolean isOpen();
    public void open();
    public void close();
    public List<Product> getProducts() throws ShopIsClosedException;
    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException;
    public float getPrice(long barcode) throws ShopIsClosedException, NoSuchProductException;
    public boolean hasProduct(long barcode) throws ShopIsClosedException;
    public void addNewProduct(Product product, int quantity, float price) throws ShopIsClosedException, ProductAlreadyExistsException;
    public void addProduct(long barcode,int quantity) throws ShopIsClosedException, NoSuchProductException;
    public Product byProduct(long barcode) throws ShopIsClosedException, NoSuchProductException, OutOfStockException;
    public List<Product> buyProducts(long barcode, int quantity) throws ShopIsClosedException, NoSuchProductException, OutOfStockException;
    public String toString();
}
