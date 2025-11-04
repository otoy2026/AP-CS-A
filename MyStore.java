/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mystore;
import java.util.Scanner;
import java.text.NumberFormat;
/**
 *
 * @author OToy2026
 */
public class MyStore {

    public static void main(String[] args) {
        Item tshirt = new Item("T Shirt", 29.99);
        System.out.println(tshirt);
        Item shoes = new Item("Shoes", 125.99);
        System.out.println(shoes);       
        Item jeans = new Item("Jeans", 60.99);
        System.out.println(jeans);
        Item jacket = new Item("Jacket", 250.00);
        System.out.println(jacket);
        Item hat = new Item("Hat", 25.99);
        System.out.println(hat);
        Item shorts = new Item("Shorts", 75.99);
        System.out.println(shorts);
        Item blouse = new Item("Blouse", 88.99);
        System.out.println(blouse);
        
        double MAX_BUDGET = 250.00;
        Scanner scan = new Scanner(System.in);
        NumberFormat USD = NumberFormat.getCurrencyInstance();

        
        double TOTAL = 0;
        
        while (TOTAL < MAX_BUDGET){
        System.out.println("\nEnter item");
        String item1 = scan.nextLine();
        if (item1.equals(tshirt.getName())){
            TOTAL = TOTAL+tshirt.getPrice();
        } else if (item1.equals(shoes.getName())){
            TOTAL = TOTAL+shoes.getPrice();
        } else if (item1.equals(jeans.getName())){
            TOTAL = TOTAL+jeans.getPrice();
        } else if (item1.equals(jacket.getName())){
            TOTAL = TOTAL+jacket.getPrice();
        } else if (item1.equals(hat.getName())){
            TOTAL = TOTAL+hat.getPrice();
        } else if (item1.equals(shorts.getName())){
            TOTAL = TOTAL+shorts.getPrice();
        } else if (item1.equals(blouse.getName())){
            TOTAL = TOTAL+blouse.getPrice();
        } else {
            System.out.println("Item not found");
        }
        
        String fTOTAL=USD.format(TOTAL);
        System.out.println("Total: "+fTOTAL);
        
        if(TOTAL>MAX_BUDGET){
            System.out.println("WARNING: Over budget!");
        }
        if(TOTAL==MAX_BUDGET){
            System.out.println("Max budget reached.");
        }
        }
    }
}


class Item {
    private String strName;
    private double dblPrice;
    
    public Item(String inName, double inPrice){
        strName = inName;
        dblPrice = inPrice;
    }
    
    public String getName(){
        return strName;}
    public double getPrice(){
        return dblPrice;}
    
    public void setName(String newName){
        strName = newName;}
    public void setPrice(double newPrice){
        dblPrice = newPrice;}
        
     @Override
    public String toString(){
        return "Item: "+strName+", Price: "+dblPrice;
    }     
}
