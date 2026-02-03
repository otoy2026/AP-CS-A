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

        System.out.println("All days of the week:");
        for (String day : weekDays) {
            System.out.println(day);
        }

        String[] weekdaysOnly = new String[5];

        for (int i = 0; i < 5; i++) {
            weekdaysOnly[i] = weekDays[i + 1];
        }

        System.out.println("\nWeekdays only:");
        for (String day : weekdaysOnly) {
            System.out.println(day);
        }
    }
}


