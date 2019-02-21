package com.codecool.api;

import java.io.*;

public class FileManager {
    
    private PlazaImpl plaza;
    
    public void fileWriter(PlazaImpl currentPlaza){
        try {
            FileOutputStream fileOut = new FileOutputStream("plaza.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(currentPlaza);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in plaza.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public PlazaImpl fileReader(){
        try {
            FileInputStream fileIn = new FileInputStream("plaza.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            plaza = (PlazaImpl) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("PlazaImpl class not found");
            c.printStackTrace();
        }
        return plaza;
    }
}
