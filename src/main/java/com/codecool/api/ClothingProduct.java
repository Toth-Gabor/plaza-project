package com.codecool.api;

import java.io.Serializable;

public class ClothingProduct extends Product implements Serializable {
    
    private String material;
    private String size;
    
    public ClothingProduct(long barcode, String name, String manufacturer, String material, String size) {
        super(barcode, name, manufacturer);
        this.material = material;
        this.size = size;
    }
    
    public String getMaterial() {
        return this.material;
    }
    
    public String getType() {
        return this.size;
    }
    
    @Override
    public String toString() {
        return "Clothing barcode: " + barcode + ", name: " + name +
        ", made of " + material + ", made by: " +
            manufacturer + ", type is: " + size;
    }
}
