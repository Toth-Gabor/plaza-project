package com.codecool.cmdProg;

import com.codecool.api.*;

public class Main {
    
    public static void main(String[] args){
        new CmdProgram(args).run();
        /*Shop shop = new ShopImpl("joli","Gyuri");
        System.out.println(shop.getName());
        shop.open();
        try {
            shop.addNewProduct(new ClothingProduct(1,"d","s","w","c"), 2, 11);
        } catch (ShopIsClosedException | ProductAlreadyExistsException e) {
            System.out.println(e.getMessage());
            
        }
        try {
            System.out.println(shop.getProducts().size());
        } catch (ShopIsClosedException e) {
            System.out.println(e.getMessage());
        }
        try {
            for (Product product : shop.getProducts()) {
                System.out.println(product);
            }
        } catch (ShopIsClosedException e) {
            System.out.println(e.getMessage());
        }*/
    }
}
