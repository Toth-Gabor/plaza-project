package com.codecool.api;

import java.util.List;

public class PlazaImpl implements Plaza {
    
    private List<Shop> shops;
    
    public PlazaImpl(List<Shop> shops) {
        this.shops = shops;
    }
    
    @Override
    public List<Shop> getShop() {
        return this.shops;
    }
    
    @Override
    public void addShop(Shop shop) {
        this.shops.add(shop);
    }
    
    @Override
    public void removeShop(Shop shop) {
        this.shops.remove(shop);
    }
    
    @Override
    public Shop findShopByName(String name) {
        for (Shop shop : shops) {
            if (shop.getName().equals(name)){
                return shop;
            }
        }
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
}
