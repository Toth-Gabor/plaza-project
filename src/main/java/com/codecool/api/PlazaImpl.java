package com.codecool.api;

import java.util.List;

public class PlazaImpl implements Plaza {
    
    private List<Shop> shops;
    private boolean openStatus;
    private String name;
    
    public PlazaImpl(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public List<Shop> getShop() throws PlazaIsClosedException{
        if (isOpen()){
            return this.shops;
        } else {
            throw new PlazaIsClosedException("The plaza is closed!");
        }
    }
    
    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException{
        if (shops.contains(shop)){
            throw new ShopAlreadyExistsException("This shop already exists!");
        } else if (!isOpen()){
            throw new PlazaIsClosedException("The plaza is closed!");
        } else {
            shops.add(shop);
        }
    }
    
    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException{
        if (!isOpen()){
            throw new PlazaIsClosedException("The plaza is closed!");
        } else if (!shops.contains(shop)){
            throw new NoSuchShopException("No such shop!");
        } else {
            shops.remove(shop);
        }
    }
    
    @Override
    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException{
        if (!isOpen()){
            throw new PlazaIsClosedException("The plaza is closed!");
        } else {
            for (Shop shop : shops) {
                if (shop.getName().equals(name)){
                    return shop;
                } else {
                    throw new NoSuchShopException("No such shop!");
                }
            }
        }
        return null;
    }
    
    @Override
    public boolean isOpen() {
        return this.openStatus;
    }
    
    @Override
    public void open() {
        this.openStatus = true;
    }
    
    @Override
    public void close() {
        this.openStatus = false;
    }
    
    @Override
    public String toString() {
        return "PlazaImpl{" +
            "shops=" + shops +
            ", openStatus=" + openStatus +
            ", name='" + name + '\'' +
            '}';
    }
}
