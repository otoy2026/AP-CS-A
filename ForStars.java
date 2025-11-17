/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.forstars;

/**
 *
 * @author OToy2026
 */
public class ForStars {

    public static void main(String[] args) {
        final int Max_Rows=10;
       
        //print out pattern number
        System.out.println("A.");
        //create for loop, where rowsA equals 1, and it increases until RowsA is no longer <= Max_Rows
        for (int rowsA=1; rowsA<=Max_Rows; rowsA++){
            //create for loop, where starA equals 10, and decreases until it is no longer greater than rowsA
            for (int starA = Max_Rows; starA >= rowsA; starA--){
                //print a star
                System.out.print("*");
            }
        //print out the result of both loops combined
        System.out.println();
        }
        
//-----------------------------------------------------------------

    
        System.out.println("\nB.");
        for (int rowsB=1; rowsB<=Max_Rows; rowsB++){
            //create a loop for "space" that allows the stars to begin later in the line
            for (int spaceB=(Max_Rows-1); spaceB>=rowsB; spaceB--){
                System.out.print(" ");
                }
            //use for loop to print out stars
            for (int starB=(Max_Rows+1); (starB-rowsB)<=Max_Rows; starB++){
                System.out.print("*");
            }
        System.out.println();
        }
        
//-----------------------------------------------------------------
        
        System.out.println("\nC.");
        for (int rowsC=1; rowsC<=Max_Rows; rowsC++){
            for (int spaceC=2; spaceC<=rowsC; spaceC++){
                System.out.print(" ");
                }
            for (int starC=Max_Rows+1; (starC-rowsC)>=1; starC-- ){
                System.out.print("*");
            }
        System.out.println();
        }
        
//-----------------------------------------------------------------        
        //create two different "row" loops for ease of programming
        //create first for loop for top half
        System.out.println("\nD.");
        for (int rowsD=1; rowsD<=(Max_Rows-5); rowsD++){
            for (int spaceD=4; spaceD>=rowsD; spaceD--){
                System.out.print(" ");
            }
            for (int starD=Max_Rows+1; starD-rowsD<=(Max_Rows+(rowsD-1)); starD++){
                System.out.print("*");
            }
        System.out.println();
        }   
        
        //create second for loop for bottom half
        for (int rows2D=1; rows2D<=(Max_Rows-5); rows2D++){
            for (int space2D=2; space2D<=rows2D; space2D++){
                System.out.print(" ");
            }
            for (int star2D=Max_Rows+1; star2D-rows2D>=(rows2D+1); star2D--){
               System.out.print("*");
            }
        System.out.println();
        }
        
    }
}
