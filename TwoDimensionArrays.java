/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.twodimensionarrays;
import java.util.Random;
/**
 *
 * @author OToy2026
 */
public class TwoDimensionArrays {

    public static void main(String[] args) {
 
    //create strings for suit and number
    String[] suit = {"Hearts", "Diamonds", "Clubs", "Spades"};
    String[] number = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    //create 2D array
    int[][] deck = new int[52][2];
    
    //shuffle deck
    boolean[][] picked = new boolean[suit.length][number.length];
    for(int i=0; i<suit.length; i++){
        for(int j=0; j<number.length; j++){
            picked[i][j]=false;
        }
    }
    
    Random random = new Random();
    for (int i=0; i<52; i++){
        int randSuit = random.nextInt(suit.length);
        int randNum = random.nextInt(number.length);
        while(picked[randSuit][randNum]){
            randSuit = random.nextInt(suit.length);
            randNum = random.nextInt(number.length);
        }
        deck[i][0]= randSuit;
        deck[i][1]= randNum;
        picked[randSuit][randNum]=true;
    }
    //print hands
    int count = 0;
    for (int h = 1; h <= 4; h++) {
        System.out.println("Hand " + h + ":");
    
    for (int card = 0; card < 5; card++) {
        int suit1 = deck[count][0];
        int num1 = deck[count][1];
        
        System.out.println(number[num1] + " of " + suit[suit1]);
        count++; 
    }
    System.out.println(); 
    }
    
    }
}
