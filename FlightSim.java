/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package flightsim;

/**
 *
 * @author OToy2026
 */

import java.io.IOException;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class FlightSim {
    
    public static void main(String[] args) {
      
        System.out.println("Welcome to Flight Simulator.");
        
        Path path1 = Paths.get("Info", "flights.txt");
        ArrayList<flightPath> flights = new ArrayList<>();
        
        if (Files.exists(path1)){
        try  {
            Scanner scan1 = new Scanner(path1);
            while (scan1.hasNextLine()) {
                String line = scan1.nextLine();
                String[] parts = line.split(",");
                
                String company = parts[0];
                String aircraft = parts[1];
                String startPoint = parts[2];
                String endPoint = parts[3];
                int miles = Integer.parseInt(parts[4]);
                int elapsed = Integer.parseInt(parts[5]);
                
                flights.add(new flightPath(company, aircraft, startPoint, endPoint, miles, elapsed));
            } 
        } catch (IOException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        }
        
        Random rand = new Random();
        int randomFlight = rand.nextInt(flights.size());
        System.out.println(flights.get(randomFlight));
        
        //-----------------------------------------------------------------
        
        Path path2 = Paths.get("Info", "prompts.txt");
        ArrayList<promptFormat> prompts = new ArrayList<>();
        
        if (Files.exists(path2)) {
            try (Scanner scan2 = new Scanner(path2)) {
                scan2.useDelimiter("-"); 
                while (scan2.hasNext()) {
                    String content = scan2.next().trim();
                    if (!content.isEmpty()) {
                    prompts.add(new promptFormat(content));
                    }
                }
        
    } catch (IOException e) {
        System.err.println("Error reading prompts: " + e.getMessage());
    }
}
        
        //-----------------------------------------------------------------
        
        System.out.println(prompts.get(0));
        Scanner input = new Scanner(System.in);
        int choice1 = input.nextInt();
        if(choice1==1){
            System.out.println(prompts.get(1));
        }else{
            System.out.println(prompts.get(2));
        }
        int rand1 = rand.nextInt(2);
        if(rand1==1){
            System.out.println(prompts.get(3));
            int choice2 = input.nextInt();
            if(choice2==1){
                System.out.println(prompts.get(5));
                int choice3 = input.nextInt();
                if (choice3==1){
                    System.out.println(prompts.get(6));
                    int answer = input.nextInt();
                    if (answer==132){
                        System.out.println(prompts.get(7));
                        System.out.println(prompts.get(9));
                    }
                }           
            }else if(choice2==2){
                    
            }else{
                System.out.println("Invalid input.");
            }
        }else{
            System.out.println(prompts.get(4));
            int choice2 = input.nextInt();
            if(choice2==1){
                System.out.println(prompts.get(6));
                int answer = input.nextInt();
                if (answer==132){
                    System.out.println(prompts.get(7));
                    System.out.println(prompts.get(9));
                }else{
                    System.out.println(prompts.get(8));
                    System.out.println(prompts.get(9));
                }
            }else if(choice2==2){
                System.out.println(prompts.get(9));
            }else{
                System.out.println("Invalid input.");
            }
        }      
    }
}

//-----------------------------------------------------------------

class flightPath{
    
    //declare flightPath variables
    private String company, aircraft, startPoint, endPoint;
    private int miles, elapsed;
    private String flightSum;
    
    public flightPath (String inCompany, String inAircraft, String inStart, String inEnd, int inMiles, int inElapsed){
        company = inCompany;
        aircraft = inAircraft;
        startPoint = inStart;
        endPoint = inEnd;
        miles = inMiles;
        elapsed = inElapsed;
        //create string to return
        this.flightSum = "\nToday you will be flying a "+company+" "+aircraft+" from "+startPoint+" to "+endPoint+". The trip will cover "+miles+" miles and take an estimated "+elapsed+" minutes.";    
    }
    
    //return flightSum; 
    @Override
        public String toString() {
            return flightSum;
    }
}

//-----------------------------------------------------------------

class promptFormat{
    
    //declare variables
    private String textBody;
    private String[] newText;
    
    public promptFormat(String inText){
        textBody = inText;
        newText = textBody.split("\\~");
        
    }
    
    //return text
    @Override
        public String toString() {
            return String.join("\n", newText);
    }
    
}

//-----------------------------------------------------------------

class flightState{
    
}

//-----------------------------------------------------------------

class navStatus{

}

//-----------------------------------------------------------------

class aircraftHealth{
    
            
}

class sysHealth{
    
    int sysInitial = 100;
    boolean commsOn;
    boolean controlsOn = false;
    boolean leftEngine;
    boolean rightEngine;
    
    public boolean commsFail(){
        commsOn = false;
        return false;
    }
    public boolean controlsFail(){
        controlsOn = false;
        return false;
    }
    public boolean leftEngineFail(){
        leftEngine = false;
        return false;
    }
    public boolean rightEngineFail(){
        rightEngine = false;
        return false;
    }
}

class bodyHealth{
   
    int wingInitial = 100;
    int fuselageInitial = 100;
    
}


