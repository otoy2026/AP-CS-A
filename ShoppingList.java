/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.shoppinglist;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author OToy2026
 */
public class ShoppingList {
    public static void main(String[] args) {
        //set up scanner
        Scanner scan = new Scanner(System.in);
        //set up arraylist
        ArrayList AL = new ArrayList();

        //print instructions
        System.out.println("Enter all items:");
        System.out.println("Enter 'done' to complete list");

        //create string from input
        String item = scan.nextLine();
        //add items to arraylsit until user enters "done"
        while (!item.equals("done")) {
            AL.add(item);
            item = scan.nextLine();
        }
        
        //print list
        System.out.println("\nOriginal List:");
        //number items in list for pritning
        for (int count = 0; count < AL.size(); count++) {
            System.out.println((count + 1) + ". " + AL.get(count));
        }
        
        //second set of instructions
        System.out.println("\nEnter number(s) to remove items (one at a time).");
        System.out.println("Enter 'done' to complete list");

        //create string from input  
        String numRemove = scan.nextLine();
        //remove numbers until user enters done
        while (!numRemove.equals("done")) {
            //find index of number to remove
            int index = Integer.parseInt(numRemove)-1;
            //remove item from list
            if (index >= 0 && index < AL.size()) {
                AL.remove(index);
            }
            System.out.println("\nUpdated List:");
            for (int count = 0; count < AL.size(); count++) {
            System.out.println((count + 1) + ". " + AL.get(count));
            }
            numRemove=scan.nextLine();
        }
    }
}


