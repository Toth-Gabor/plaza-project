package com.codecool.cmdProg;

import com.codecool.api.*;

import java.util.List;
import java.util.Scanner;

public class CmdProgram {
    
    private List<Product> cart;
    private List<Float> prices;
    private Scanner scanner = new Scanner(System.in);
    private PlazaImpl plaza;
    
    public CmdProgram(String[] args) {
    
    }
    
    public void run() {
        String[] menuOptions = new String[]{"to create a new plaza.", "to exit"};
        Menu menu = new Menu("Plaza project", menuOptions);
        menu.displayMenu();
        
        while (true) {
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Please give a name for this plaza");
                    plaza = createPlaza(scanner.nextLine());
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
        String[] plazaMenuOptions = new String[]{"to list all shops.", "to add a new shop.", "to remove an existing shop.",
                                                "enter a shop by name.", "to open the plaza.", "to close the plaza.",
                                                "to check if the plaza is open or not.", "leave plaza."};
    
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
                    } catch (PlazaIsClosedException pe) {
                        System.out.println(pe.getMessage());
                    } catch (ShopAlreadyExistsException sa) {
                        System.out.println(sa.getMessage());
                    }
                    break;
                case "3":
                    try {
                        removeExistingShop();
                    } catch (NoSuchShopException e) {
                        System.out.println(e.getMessage());
                    } catch (PlazaIsClosedException pe) {
                        System.out.println(pe.getMessage());                    }
                    break;
                case "4":
    
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
            for (Shop shop : plaza.getShop()) {
                System.out.println(shop);
            }
        } else {
            throw new PlazaIsClosedException("Please open the plaza before list all shops!");
        }
        
    }
    private void addNewShop() throws PlazaIsClosedException, ShopAlreadyExistsException {
        if (plaza.isOpen()){
            System.out.print("Give a shop name: ");
            String shopName = scanner.nextLine();
            System.out.print("Give the shop owner name: ");
            String shopOwnerName = scanner.nextLine();
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
}

