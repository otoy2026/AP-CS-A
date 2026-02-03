/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.arrayassignment;

/**
 *
 * @author OToy2026
 */
public class ArrayAssignment {
    public static void main(String[] args) {

        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        System.out.println("All days:");
        for (String day : weekDays) {
            System.out.println(day);
        }

        //create new array with smaller size
        String[] weekDays2 = new String[5];
        //create for loop that copies correct week days over
        for (int copy=0; copy<5; copy++) {
            weekDays2[copy] = weekDays[copy + 1];
        }

        System.out.println("\nWeekdays:");
        for (String day : weekDays2) {
            System.out.println(day);    
        }
    }
}


