package com.codecool.api;

import java.util.List;

public interface Plaza {
    
    List<Shop> getShop() throws PlazaIsClosedException;
    
    void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException;
    
    void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException;
    
    Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException;
    
    boolean isOpen();
    
    void open();
    
    void close();
    
    String toString();
    
}
