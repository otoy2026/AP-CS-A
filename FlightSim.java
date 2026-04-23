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
        
        Path path = Paths.get("Info", "flights.txt");
        ArrayList<flightPath> flights = new ArrayList<>();
        
        if (Files.exists(path)){
        try  {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
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
        int randomNum = rand.nextInt(flights.size());
        System.out.println(flights.get(randomNum));
        
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
        this.flightSum = "Today you will be flying a "+company+" "+aircraft+" from "+startPoint+" to "+endPoint+". The trip will cover "+miles+" miles and take an estimated "+elapsed+" minutes.";    
    }
    
    //return flightSum; 
    @Override
        public String toString() {
            return flightSum;
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


