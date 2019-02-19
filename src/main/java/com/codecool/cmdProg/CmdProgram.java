package com.codecool.cmdProg;

import com.codecool.api.*;

import java.util.List;
import java.util.Scanner;

public class CmdProgram {
    
    private List<Product> cart;
    private List<Float> prices;
    private Scanner scanner = new Scanner(System.in);
    private PlazaImpl plaza;
    private Shop currentShop;
    
    public CmdProgram(String[] args) {
    
    }
    
    public void run() {
        String[] menuOptions = new String[]{"to create a new plaza.", "to exit"};
        Menu menu = new Menu("Plaza project", menuOptions);
        String plazaName;
        
        while (true) {
            menu.displayMenu();
            switch (scanner.nextLine()) {
                case "1":
                    while(true){
                        System.out.println("Please give a name for this plaza");
                        plazaName = scanner.nextLine();
                        if(plazaName.length() != 0){
                            break;
                        }
                    }
                    plaza = createPlaza(plazaName);
                    plazaMenu();
                    break;
                case "2":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Not good if you see this print!");
                    break;
            }
        }
    
    }
    
    private PlazaImpl createPlaza(String name) {
        this.plaza = new PlazaImpl(name);
        return plaza;
    }
    
    private void plazaMenu() {
        String[] plazaMenuOptions = new String[]{"to list all shops.",
                                                "to add a new shop.",
                                                "to remove an existing shop.",
                                                "enter a shop by name.",
                                                "to open the plaza.",
                                                "to close the plaza.",
                                                "to check if the plaza is open or not.",
                                                "leave plaza."};
    
        Menu plazaMenu = new Menu(("Welcome to the " + plaza.getName() + " plaza! Press"), plazaMenuOptions);
        
        while (true) {
            plazaMenu.displayMenu();
    
            switch (scanner.nextLine()) {
                case "1":
                    try {
                        listAllShops();
                    } catch (PlazaIsClosedException | NoSuchShopException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        addNewShop();
                    } catch (PlazaIsClosedException | ShopAlreadyExistsException pe) {
                        System.out.println(pe.getMessage());
                    }
                    break;
                case "3":
                    try {
                        removeExistingShop();
                    } catch (NoSuchShopException | PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        shopMenu();
                    } catch (PlazaIsClosedException | NoSuchShopException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    plaza.open();
                    break;
                case "6":
                    plaza.close();
                    break;
                case "7":
                    checkPlazaIsOpen();
                    break;
                case "8":
                    System.out.println("Thank you for visit " + plaza.getName() + " plaza!");
                    System.exit(0);
                    break;
            }
        }
    }
    private void listAllShops() throws PlazaIsClosedException, NoSuchShopException {
        if (plaza.getShop() == null){
            throw new NoSuchShopException("The plaza has not got any shop!");
        }
        if (plaza.isOpen()){
            System.out.println("Shop list:");
            for (Shop shop : plaza.getShop()) {
                System.out.println(shop);
            }
            System.out.println();
        } else {
            throw new PlazaIsClosedException("Please open the plaza before list all shops!");
        }
        
    }
    private void addNewShop() throws PlazaIsClosedException, ShopAlreadyExistsException {
        String shopName;
        String shopOwnerName;
        if (plaza.isOpen()){
            while(true){
                System.out.print("Give a shop name: ");
                shopName = scanner.nextLine();
                if(shopName.length() != 0){
                    break;
                }
            }
            while(true){
                System.out.print("Give the shop owner name: ");
                shopOwnerName = scanner.nextLine();
                if(shopOwnerName.length() != 0){
                    break;
                }
            }
            plaza.addShop(new ShopImpl(shopName, shopOwnerName));
        } else {
            throw new PlazaIsClosedException("Please open the plaza before create a new shops!");
        }
    }
    
    private void removeExistingShop() throws NoSuchShopException, PlazaIsClosedException{
        if (plaza.getShop() != null){
            System.out.print("Please add the name of the shop what you want to remove: ");
            plaza.removeShop(plaza.findShopByName(scanner.nextLine()));
        } else {
            throw new NoSuchShopException("The plaza has not got any shop!");
        }
       
    }
    
    private void checkPlazaIsOpen(){
        if (plaza.isOpen()){
            System.out.println("The plaza is open.");
        } else {
            System.out.println("The plaza is closed.");
        }
    }
    
    private void shopMenu() throws NoSuchShopException, PlazaIsClosedException {
        String[] shopMenuOptions = new String[]{"to list available products.",
                                                "to find products by name.",
                                                "to display the shop's owner.",
                                                "to open the shop.",
                                                "to close the shop.",
                                                "to add new product to the shop.",
                                                "to add existing products to the shop.",
                                                "to buy a product by barcode.",
                                                "check price by barcode.",
                                                "go back to plaza."};
        String shopName;
        Menu shopMenu;
        if (plaza.isOpen()){
            System.out.print("Please give the name of the shop: ");
            shopName = scanner.nextLine();
            if (plaza.findShopByName(shopName) != null){
                currentShop = plaza.findShopByName(shopName);
                while(true){
                    shopMenu = new Menu("Hi! This is the " + currentShop.getName() + " shop, welcome! Press", shopMenuOptions);
                    shopMenu.displayMenu();
                    
                    switch (scanner.nextLine()){
                        case "1":
                            try {
                                listAvailableProducts();
                            } catch (ShopIsClosedException | TheShopIsEmptyException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "2":
        
                            break;
                        case "3":
                            currentShop.getOwner();
                            break;
                        case "4":
                            currentShop.open();
                            break;
                        case "5":
                            currentShop.close();
                            break;
                        case "6":
        
                            break;
                        case "7":
        
                            break;
                        case "8":
        
                            break;
                        case "9":
        
                            break;
                        case "10":
                            return;
                            
                    }
                }
                
                
            } else {
                throw new NoSuchShopException("The plaza has not got any shop with this name!");
            }
        } else {
            throw new PlazaIsClosedException("The plaza is closed.");
        }
    }
    private void listAvailableProducts() throws ShopIsClosedException, TheShopIsEmptyException {
        if (currentShop.isOpen()){
            if (currentShop.getProducts().size() != 0){
                for (Product product : currentShop.getProducts()) {
                    System.out.println(product);
                }
            } else {
                throw new TheShopIsEmptyException("There are no product in this shop!");
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
    }
}

