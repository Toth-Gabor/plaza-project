package com.codecool.api;

import java.io.Serializable;
import java.util.Date;

public class FoodProduct extends Product implements Serializable {
    
    private int calories;
    private Date bestBefore;
    
    public FoodProduct(long barcode, String name, String manufacturer, int calories, Date bestBefore) {
        super(barcode, name, manufacturer);
        this.calories = calories;
        this.bestBefore = bestBefore;
    }
    
    public boolean isStillConsumable(){ // még nem jó!
        return false;
    }
    
    public int getCalories() {
        return calories;
    }
    
    @Override
    public String toString() {
        return "Food barcode: " + barcode + ", name: " + name +
                ", has " + calories + " calories " + ", made by: " +
                manufacturer + ", best before: " + bestBefore;

    }
}
