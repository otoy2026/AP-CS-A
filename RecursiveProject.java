/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recursiveproject;

/**
 *
 * @author OToy2026
 */
import java.util.Scanner;

public class RecursiveProject {

    public static int pascal(int i, int j) {
        //set up base cases
        if (j==0 ||j==i) {
            return 1;
        }
        return pascal(i-1, j-1) + pascal(i-1, j);
    }

    public static void main(String[] args) {
        //set up scanner
        Scanner scan = new Scanner(System.in);

        //ask for user to enter Nth line number
        System.out.print("Enter line number: ");
        int line = scan.nextInt();

        int[] row = new int[line + 1];

        for (int i = 0; i <= line; i++) {
            row[i] = pascal(line, i);
        }
        System.out.println("Line " + line + ":");
        for (int num : row) {
            System.out.print(num + " ");
        }
        scan.close();
    }
}
