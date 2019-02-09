package com.codecool.api;

import java.util.List;

public interface Plaza {
    
    public List<Shop> getShop();
    public void addShop(Shop shop);
    public void removeShop(Shop shop);
    public Shop findShopByName(String name);
    public boolean isOpen();
    public void open();
    public void close();
    public String toString();
    
}
