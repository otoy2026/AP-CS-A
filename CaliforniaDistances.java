/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.californiadistances;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author OToy2026
 */
public class CaliforniaDistances {

    public static void main(String[] args) {
                
            //create list of cities w/names and coordinates
            //create arraylist to check guessed city

            ArrayList<City> AL = new ArrayList<>();

            City losangeles = new City("Los Angeles", 34.0549, 118.2426, "This city is the only North American city that has hosted the Summer Olympics twice");
            AL.add(losangeles);
            
            City sandiego = new City("San Diego", 32.7157, 117.1611, "This city is home to the world's largest urban cultural park");   
            AL.add(sandiego);
       
            City sanjose = new City("San Jose", 37.3387, 121.8853, "This city is the birthplace of Eggo waffles and Chuck E. Cheese");      
            AL.add(sanjose);
           
            City sanfrancisco = new City("San Francisco", 37.77926, 122.41933, "This city was originally known as 'Yerba Buena' before being renamed in 1847.");
            AL.add(sanfrancisco);
            
            City fresno = new City("Fresno", 36.74773, 119.77237, "This city, and its surrounding area, produces more than half of the world's raisins");
            AL.add(fresno);
            
            City sacramento = new City("Sacramento", 38.58106, 121.49389, "This city shares a name with the longest river in California");
            AL.add(sacramento);
            
            City longbeach = new City("Long Beach", 33.76902, 118.19160, "This ciy is home to the second busiest seaport in the United States");
            AL.add(longbeach);
            
            City oakland = new City("Oakland", 37.80446, 122.27136, "This city has multiple redwood parks, with hundreds of acres of redwood trees accessible within city limits");
            AL.add(oakland);
            
            City bakersfield = new City("Bakersfield", 35.3787, 119.01946, "This city's baseball field is one of the only pro baseball parks where the batter faces west, into the setting sun");
            AL.add(bakersfield);
            
            City anaheim = new City("Anaheim", 33.838475, 117.91173, "This city is home to the largest convention center on the west coast");
            AL.add(anaheim);
            
//-----------------------------------------------------------------    

            //create map to route user input to correct city object
        
            Map<String, City> cities = new HashMap<>();

            cities.put("losangeles", losangeles);
            cities.put("sandiego", sandiego);
            cities.put("sanfrancisco", sanfrancisco);
            cities.put("sanjose", sanjose);
            cities.put("fresno", fresno);
            cities.put("sacramento", sacramento);
            cities.put("longbeach", longbeach);
            cities.put("oakland", oakland);
            cities.put("bakersfield", bakersfield);
            cities.put("anaheim", anaheim);
            
//-----------------------------------------------------------------
           
            //print instructions
            System.out.println("Welcome to CA CityGuessr!\n");
            System.out.println("""
                               The aim of the game is to correctly guess a city in California. 
                               To start, you will be given a trivia fact about the city to base your first guess. 
                               If you guess wrong, the game will tell you where the correct city is relative to your guess.
                               You have five guesses before the round ends.
                               Good luck!""");
            
            System.out.println("\nThe possible cities are: ");
            //create loop to print cities from array list
            for (City city : AL){
                System.out.println(city.getName());
            }

            //create scanner
            Scanner scan = new Scanner(System.in);
            boolean play = true;
            
            //create new arraylist for cities thatve been used
            ArrayList<City> ALdone = new ArrayList<>();          
            
            //create loop for playing game
            while (play){
                int attempt = 1;
                boolean done = false;
                City answer;
                
                //pick a random city (that hasnt been picked already)
                do {
                    Random generator = new Random();
                    int num = generator.nextInt(AL.size());
                    answer = AL.get(num);
                }while (ALdone.contains(answer));
                
                ALdone.add(answer);
                
                
                System.out.println("\nStarting hint: "+answer.getFact());
                System.out.println("Guess:");
                
            //create loop for each round
            while (!done){
                String input = scan.nextLine().toLowerCase().replaceAll("\\s","");
                City found = cities.get(input);

                //check if the city is valid, then if it is correct
                if (AL.contains(found)){
                    cityCompare guess = new cityCompare(found.getLat(), found.getLong(), answer.getLat(), answer.getLong());
                    
                    if (found != answer){
                        System.out.println("Nope! The correct city is "+(int)guess.distance()+" miles "+guess.direction()+ " of your guess.");  
                    }else{
                        done = true;
                    }
                }else{
                    System.out.println("City not recognized! Try again.");
                }
                
                //end game if user goes over 5 guesses
                if (attempt >= 5){
                    System.out.println("\nOut of guesses.");
                    done=true;
                }
                attempt++;
            }
            
            System.out.println(answer.cityName+" was the correct city!");
            
            //create if loop to stop game when all cities have been used
            if (ALdone.size()<AL.size()){
                
                System.out.println("Play again? (yes/no)");
                String repeat = scan.nextLine();
            
                if(!repeat.equalsIgnoreCase("yes")){
                    play = false;
                    System.out.println("Game Completed! Thanks for playing!");
            } } else{
                play = true;
                System.out.println("\nGame Completed! Thanks for playing!");   
            }
        }
    }
}

//-----------------------------------------------------------------

class City {
    
    double latitude, longitude;
    String cityName, fact;
    
    //create City constructor
    public City (String inCityName, double inLatitude, double inLongitude, String inFact){
        latitude = inLatitude;
        longitude = inLongitude;
        cityName = inCityName;
        fact = inFact;
    }
    
    public double getLat(){
        return latitude;}
    public double getLong(){
        return longitude;}
    public String getName(){
        return cityName;} 
    public String getFact(){
        return fact;}
}

 //-----------------------------------------------------------------

class cityCompare{
    
    //declare latitude and longitude variables
    double lat1, long1, lat2, long2;
    int R = 6371;
    
    public cityCompare(double inlat1, double inlong1, double inlat2, double inlong2){
        //convert degree coordinates to radians for later use
        lat1 = Math.toRadians(inlat1);
        long1 = Math.toRadians(inlong1);
        lat2 = Math.toRadians(inlat2);
        long2 = Math.toRadians(inlong2);
    }
    
    //-----------------------------------------------------------------    
    
    public double distance(){
        //use haversine formula for spherical distance
        double kmDist = 2*R*
                Math.asin(Math.sqrt((Math.pow(Math.sin((lat2-lat1) / 2), 2)
                + Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin((long2-long1)/2), 2))));
        
        //convert to miles and return
        double miDist = kmDist/1.6093;
        return miDist;
    }
    
    //-----------------------------------------------------------------
    
    //determine cardinal direction of correct answer from guessed city
    public String direction(){
        double latDist = lat2-lat1;
        double longDist = long2-long1;
        String cardinal;
        
        //   three factors in determining direction:
        //      1. absolute value of latitude distance VS absolute value of longitude distance
        //         (determines if distance from guess is greater vertically or horizontally.)
        //      2. absolute value of latitude distance minus longitude distance
        //         (determines if direction should be cardinal or intercardinal)
        //      3. if latitude distance or longitude distance is greater than or less than 0
        //         (determines north/south (north>0) or east/west (west>0)
        
        if (Math.abs(latDist)>Math.abs(longDist) && Math.abs(latDist-longDist)>=0.2 && latDist>0){
            cardinal = "north";
            return cardinal;
        }else if(Math.abs(latDist)>Math.abs(longDist) && Math.abs(latDist-longDist)>=0.2 && latDist<0){
            cardinal = "south";
            return cardinal;
        }else if(Math.abs(latDist)<Math.abs(longDist) && Math.abs(latDist-longDist)>=0.2 && longDist<0){
            cardinal = "east";
            return cardinal;
        }else if(Math.abs(latDist)<Math.abs(longDist) && Math.abs(latDist-longDist)>=0.2 && longDist>0){
            cardinal = "west";
            return cardinal;
        
        //-----------------------------------------------------------------
        
        //determining intercardinal direction (if necessary)
       
        }else if((Math.abs(latDist)>Math.abs(longDist) && Math.abs(latDist-longDist)<=0.1 && latDist>0 && longDist<0) ||
                (Math.abs(latDist)<Math.abs(longDist) && Math.abs(latDist-longDist)<=0.1 && latDist>0 && longDist<0)){
            cardinal = "northeast";
            return cardinal;
        }else if((Math.abs(latDist)>Math.abs(longDist) && Math.abs(latDist-longDist)<=0.1 && latDist<0 && longDist<0) ||
                (Math.abs(latDist)<Math.abs(longDist) && Math.abs(latDist-longDist)<=0.1 && latDist<0 && longDist<0)){
            cardinal = "southeast";
            return cardinal;
        }else if((Math.abs(latDist)>Math.abs(longDist) && Math.abs(latDist-longDist)<=0.1 && latDist<0 && longDist>0) ||
                (Math.abs(latDist)<Math.abs(longDist) && Math.abs(latDist-longDist)<=0.1 && latDist<0 && longDist>0)){
            cardinal = "southwest";
            return cardinal;
        }else if((Math.abs(latDist)>Math.abs(longDist) && Math.abs(latDist-longDist)<=0.1 && latDist>0 && longDist>0) ||
                (Math.abs(latDist)<Math.abs(longDist) && Math.abs(latDist-longDist)<=0.1 && latDist>0 && longDist>0)){
            cardinal = "northwest";
            return cardinal;
        }else{
            return "error!";
        }
    }
}    






