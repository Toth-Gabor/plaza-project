package com.codecool.cmdProg;

import com.codecool.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {
    
    private List<Product> cart = new ArrayList<>();
    private List<Float> prices;
    private Scanner scanner = new Scanner(System.in);
    private PlazaImpl plaza;
    private Shop currentShop;
    private FileManager fm = new FileManager();
    
    public CmdProgram(String[] args) {
    
    }
    
    public void run() {
        String[] menuOptions = new String[]{"to create a new plaza.", "to enter the default plaza", "to load saved plaza", "to exit"};
        Menu menu = new Menu("Plaza project", menuOptions);
        String plazaName;
        
        while (true) {
            menu.displayMenu();
            switch (scanner.nextLine()) {
                case "1":
                    while(true){
                        System.out.print("Please give a name for this plaza: ");
                        plazaName = scanner.nextLine();
                        if(plazaName.length() != 0){
                            break;
                        }
                    }
                    plaza = createPlaza(plazaName);
                    plazaMenu();
                    break;
                case "2":
                    try {
                        defaultPlaza();
                    } catch (ShopAlreadyExistsException | PlazaIsClosedException | ProductAlreadyExistsException |
                            ShopIsClosedException | ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    plaza = fm.fileReader();
                    plazaMenu();
                    break;
                case "4":
                    System.exit(0);
                    break;
            }
        }
    
    }
    
    private PlazaImpl createPlaza(String name) {
        this.plaza = new PlazaImpl(name);
        return plaza;
    }
    
    private void defaultPlaza() throws ShopAlreadyExistsException, PlazaIsClosedException, ProductAlreadyExistsException, ShopIsClosedException, ParseException {
        plaza = new PlazaImpl("Default");
        plaza.open();
        plaza.addShop(new ShopImpl("Fashion", "Mr X"));
        plaza.addShop(new ShopImpl("Faloda", "Jumanji"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        plaza.getShop().get(1).open();
        Date date = df.parse("2019-02-29");
        plaza.getShop().get(1).addNewProduct(new FoodProduct
            (1234, "Gyros", "Aladdin", 600, date), 10, 900);
        plazaMenu();
        
    }
    
    private void plazaMenu() {
        String[] plazaMenuOptions = new String[]{"to list all shops.",
                                                "to add a new shop.",
                                                "to remove an existing shop.",
                                                "enter a shop by name.",
                                                "to open the plaza.",
                                                "to close the plaza.",
                                                "to check if the plaza is open or not.",
                                                "to save current plaza",
                                                "to check bought items",
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
                    fm.fileWriter(plaza);
                    break;
                case "9":
                    try {
                        showBoughtItems();
                    } catch (NotBoughtAnyItemsException | PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "10":
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
    
    private void showBoughtItems() throws NotBoughtAnyItemsException, PlazaIsClosedException {
        if (plaza.isOpen()){
            if (cart.size() != 0){
                for (Product product : cart) {
                    System.out.println(product);
                }
            } else {
                throw new NotBoughtAnyItemsException("There are not any bought item(s)!");
            }
        } else {
            throw new PlazaIsClosedException("The plaza is closed.");
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
                            } catch (ShopIsClosedException | TheShopIsEmptyException | NoSuchProductException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "2":
                            try {
                                findProductByName();
                            } catch (ShopIsClosedException | NoSuchProductException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "3":
                            System.out.println("The shop owner is " + currentShop.getOwner());
                            break;
                        case "4":
                            currentShop.open();
                            break;
                        case "5":
                            currentShop.close();
                            break;
                        case "6":
                            try {
                                addNewProductToTheShop();
                            } catch (ShopIsClosedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "7":
                            try {
                                addExistingProduct();
                            } catch (NoSuchProductException | ShopIsClosedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "8":
                            try {
                                byProductByBarcode();
                            } catch (NoSuchProductException | ShopIsClosedException | OutOfStockException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "9":
                            try {
                                checkProductPriceByBarcode();
                            } catch (NoSuchProductException | ShopIsClosedException e) {
                                System.out.println(e.getMessage());
                            }
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
    private void listAvailableProducts() throws ShopIsClosedException, TheShopIsEmptyException, NoSuchProductException {
        if (currentShop.isOpen()){
            if (currentShop.getProducts().size() != 0){
                System.out.println("Shop items:");
                for (Product product : currentShop.getProducts()) {
                    System.out.println(" " + currentShop.getQuantity(product.getBarcode()) + " piece of " + product);
                }
                System.out.println();
            } else {
                throw new TheShopIsEmptyException("There are no product in this shop!");
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
    }
    
    private void findProductByName() throws ShopIsClosedException, NoSuchProductException {
        System.out.print("Please give the shop name:");
        String name = scanner.nextLine();
        if (currentShop.isOpen()){
            if(currentShop.getProducts().contains(currentShop.findByName(name))){
                System.out.println(currentShop.findByName(name));
            } else {
                throw new NoSuchProductException("No product by the given name!");
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
    }
    
    private void addNewProductToTheShop() throws ShopIsClosedException {
        String productType;
        FoodProduct food = null;
        ClothingProduct clothingProduct = null;
    
        if (currentShop.isOpen()){
            while(true){
                System.out.print("Please choose a product type:[food or clothing]: ");
                productType = scanner.nextLine();
                
                if (productType.toLowerCase().equals("food")){
                    try {
                        addFoodProduct(currentShop);
                    } catch (ParseException | ProductAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                    break;
                } else if (productType.toLowerCase().equals("clothing")){
                    try {
                        addClothingProduct(currentShop);
                    } catch (ProductAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
    }
    
    private void addFoodProduct(Shop shop) throws NumberFormatException, ParseException, ProductAlreadyExistsException, ShopIsClosedException {
        String[] questions = new String[]{"Give a barcode: ",
                                        "Give the food name: ",
                                        "Give a manufacturer: ",
                                        "Give the calories: ",
                                        "Give the experience date [yyyy-MM-dd]: "};
        Long barcode;
        Date date;
        
        System.out.print("Give a barcode: ");
        barcode = Long.valueOf(scanner.nextLine());
        
        System.out.print("Give the food name: ");
        String name = scanner.nextLine();
        System.out.print("Give a manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Give the calories: ");
        int calories = Integer.parseInt(scanner.nextLine());
        System.out.print("Give the experience date [yyyy-MM-dd]: ");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        date = df.parse(scanner.nextLine());
        System.out.print("Give the quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Give the price/product: ");
        Float price = Float.valueOf(scanner.nextLine());
    
        shop.addNewProduct(new FoodProduct(barcode, name, manufacturer, calories, date), quantity, price);
    }
    
    private void addClothingProduct(Shop shop) throws NumberFormatException, ProductAlreadyExistsException, ShopIsClosedException {
        String[] questions = new String[]{"Give the clothes name: ",
                                        "Give a manufacturer: ",
                                        "Give the material: ",
                                        "Give the type: "};
        String[] answers = new String[questions.length];
        Long barcode;
        System.out.print("Give a barcode: ");
        barcode = Long.valueOf(scanner.nextLine());
        for (int i = 0; i < questions.length; i++) {
            System.out.print(questions[i]);
            answers[i] = scanner.nextLine();
        }
        System.out.print("Give the quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Give the price/product: ");
        Float price = Float.valueOf(scanner.nextLine());
        shop.addNewProduct(new ClothingProduct(barcode, answers[0], answers[1], answers[2], answers[3]), quantity, price);
    }
    
    private void addExistingProduct() throws NoSuchProductException, ShopIsClosedException {
        
        System.out.print("Give the barcode of the item: ");
        Long barcode = Long.valueOf(scanner.nextLine());
        System.out.print("Give the quantity of the items: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        currentShop.addProduct(barcode, quantity);
    }
    
    private void byProductByBarcode() throws NoSuchProductException, ShopIsClosedException, OutOfStockException {
        System.out.print("Give a barcode: ");
        Long barcode = Long.valueOf(scanner.nextLine());
        System.out.println("Bought 1 " + currentShop.getProductByBarcode(barcode));
        cart.add(currentShop.byProduct(barcode));
        
    }
    private void checkProductPriceByBarcode() throws NoSuchProductException, ShopIsClosedException {
        System.out.print("Give a barcode: ");
        Long barcode = Long.valueOf(scanner.nextLine());
        System.out.println(currentShop.getPrice(barcode));
        System.out.println();
    }
}

