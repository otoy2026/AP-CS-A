package com.mycompany.radiuscalc;

import java.util.Scanner;
import java.math.BigDecimal;

public class RadiusCalc {

    /**
     * @param args the command line arguments
     */
    //carry out calculations or circular items
    //such as a circle, a sphere, a cone, a column
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("This program calculates round item numbers such as \n" +
                "1 - Area of a circle (pi r^2)\n" +
                "2 - Volume of a sphere (4/3 pi r^3)\n" +
                "3 - Volume of a cone (pi r^2 h/3)\n" +
                "4 - Volume of a column (pi r^2 h)");
        System.out.println("Type the number for which one you want to calculate");
        
        //check for numerical input
        if (!s.hasNextInt()){
            System.out.println("Try again. Enter a number 1-4");
            return;
        }
        
        int i = s.nextInt();
        
        if (i == 1){
            cCalc();
        }else if (i == 2){
            sCalc();
        }else if(i == 3){
            cCalc3();
        }else if(i == 4){
            cCalc4();
        }else
            System.out.println("Try again. Enter a number 1-4");
    }
    
   
    public static void cCalc(){
        //add scanner
        Scanner s = new Scanner(System.in);
        
        System.out.println("enter the radius of your circle");
        
        //check for numerical input
        if (!s.hasNextDouble()){
            System.out.println("Try again. Enter a number");
            return;
        }
        //change input from int to double to include decimal places
        double r = s.nextDouble();
        double result = Math.PI * Math.pow(r, 2);
        //add rounding
        double rounded = Math.round(result * 100.0) / 100.0;
        System.out.println(rounded);
    }
    
    public static void sCalc(){
        Scanner s = new Scanner(System.in);
        
        System.out.println("enter the radius of your sphere");
        
        //check for numerical input        
        if (!s.hasNextDouble()){
            System.out.println("Try again. Enter a number");
            return;
        }
        double r = s.nextDouble();
        //correct (4/3) to (4.0/3.0)
        double result = (4.0/3.0) * Math.PI * Math.pow(r, 3);
        //add rounding
        double rounded = Math.round(result * 100.0) / 100.0;
        System.out.println(rounded);
    }
    
    public static void cCalc3(){
        Scanner s = new Scanner(System.in);

        System.out.println("enter the radius of your cone, then height of your cone");
                
        //check for numerical input
        if (!s.hasNextDouble()){
            System.out.println("Try again. Enter a number");
            return;
        }
        double r = s.nextDouble();
        
        if (!s.hasNextDouble()){
            System.out.println("Try again. Enter a number");
            return;
        }
        
        double h = s.nextDouble();
        //add correct equation for volume of a cone
        double result =  (1.0/3.0)*h * Math.PI * Math.pow(r, 2);
        //add rounding
        double rounded = Math.round(result * 100.0) / 100.0;
        System.out.println(rounded);
    }
    
    public static void cCalc4(){
        Scanner s = new Scanner(System.in);
        
        //update directions
        System.out.println("enter the radius of your column, then height of your column");
                
        //check for numerical input
        if (!s.hasNextDouble()){
            System.out.println("Try again. Enter a number");
            return;
        }
        double r = s.nextDouble();
        //add height input
        
        if (!s.hasNextDouble()){
            System.out.println("Try again. Enter a number");
            return;
        }
        
        double h = s.nextDouble();
        double result = Math.PI * Math.pow(r, 2) * h;
        //add rounding
        double rounded = Math.round(result * 100.0) / 100.0;
        System.out.println(rounded);
    }
}
    
 