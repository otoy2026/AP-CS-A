/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package flightsim;

/**
 *
 * @author OToy2026
 */

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FlightSim {

    public static void main(String[] args) {

        System.out.println("""
                      ======================================
                           Welcome to Flight Simulator.
                          ==============================""");

        //load flight paths
        Path path1 = Paths.get("Info", "flights.txt");
        ArrayList<flightPath> flights = new ArrayList<>();

        if (Files.exists(path1)) {
            try (Scanner scan1 = new Scanner(path1)) {
                while (scan1.hasNextLine()) {
                    String line = scan1.nextLine();
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split(",");
                    String company    = parts[0];
                    String aircraft   = parts[1];
                    String startPoint = parts[2];
                    String endPoint   = parts[3];
                    int miles         = Integer.parseInt(parts[4]);
                    int elapsed       = Integer.parseInt(parts[5]);
                    flights.add(new flightPath(company, aircraft, startPoint, endPoint, miles, elapsed));
                }
            } catch (IOException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }

        Random rand = new Random();
        int randomFlight = rand.nextInt(flights.size());
        flightPath todaysFlight = flights.get(randomFlight);
        System.out.println(todaysFlight);

        //load prompt list
        Path path2 = Paths.get("Info", "prompts.txt");
        ArrayList<promptFormat> prompts = new ArrayList<>();

        if (Files.exists(path2)) {
            try (Scanner scan2 = new Scanner(path2)) {
                scan2.useDelimiter("###");
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

        Scanner input = new Scanner(System.in);

        //pre-takeoff
        System.out.println(prompts.get(0));
        int choice1 = input.nextInt();
        boolean verifiedWithATC;
        if (choice1 == 1) {
            System.out.println(prompts.get(1)); 
            verifiedWithATC = true;
        } else {
            System.out.println(prompts.get(2)); 
            verifiedWithATC = false;
        }

        //random weather/event
        int rand1 = rand.nextInt(3);

        if (rand1 == 1) {

            //clear weather
            System.out.println(prompts.get(3));
            int choice2 = input.nextInt();

            if (choice2 == 1) {
                //glide
                System.out.println(prompts.get(5));
                int choice3 = input.nextInt();

                if (choice3 == 1) {
                    //attempt to fix radio
                    System.out.println(prompts.get(6));
                    int answer = input.nextInt();

                    if (answer == 132) {
                        System.out.println(prompts.get(7));  //correct answer
                        System.out.println(prompts.get(10)); //atc guides
                        attemptLanding(input, rand, prompts); //landing gear check
                    } else {
                        System.out.println(prompts.get(8));  //incorrect answer
                        System.out.println(prompts.get(11)); //independent choice
                        int choice4 = input.nextInt();
                        if (choice4 == 1) {
                            attemptLanding(input, rand, prompts);
                        } else {
                            System.out.println(prompts.get(14)); //keep flying, engine fails
                        }
                    }

                } else {
                    //no radio option
                    System.out.println(prompts.get(9));  //engine error
                    System.out.println(prompts.get(11)); //attempt landing choice
                    int choice4 = input.nextInt();
                    if (choice4 == 1) {
                        attemptLanding(input, rand, prompts);
                    } else {
                        System.out.println(prompts.get(14)); //keep flying, engine fails
                    }
                }

            } else if (choice2 == 2) {
                //full throttle option
                System.out.println(prompts.get(15));
                int choice3 = input.nextInt();
                if (choice3 == 1) {
                    attemptLanding(input, rand, prompts);
                } else {
                    System.out.println(prompts.get(13)); //push to far, crash
                }

            } else {
                System.out.println("Invalid input.");
            }

        } else if (rand1 == 0) {

            //stormy weather, flap comes loose
            System.out.println(prompts.get(4));
            int choice2 = input.nextInt();

            if (choice2 == 1) {
                if (verifiedWithATC) {
                    System.out.println(prompts.get(16)); //atc fast reponse
                } else {
                    System.out.println(prompts.get(17)); //atc slow response
                }
                attemptLanding(input, rand, prompts);

            } else if (choice2 == 2) {
                //no reporting
                System.out.println(prompts.get(9));  //flap gets worse
                System.out.println(prompts.get(18)); //flap detaches choice
                int choice3 = input.nextInt();
                if (choice3 == 1) {
                    attemptLanding(input, rand, prompts);
                } else {
                    System.out.println(prompts.get(13)); //instability, crash
                }

            } else {
                System.out.println("Invalid input.");
            }

        } else {

            //fuel warning
            System.out.println(prompts.get(22)); //low fuel gauge choice
            int choice2 = input.nextInt();

            if (choice2 == 1) {
                //divert
                System.out.println(prompts.get(23));
                attemptLanding(input, rand, prompts);

            } else if (choice2 == 2) {
                //fuel puzzle questioin
                System.out.println(prompts.get(24));
                int answer = input.nextInt();

                if (answer == 56) {
                    //correct answer, enough fuel
                    System.out.println(prompts.get(25));
                    attemptLanding(input, rand, prompts);
                } else {
                    //incorrect answer, crash
                    System.out.println(prompts.get(26));
                }

            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    //landing gear (before every landing)
    static void attemptLanding(Scanner input, Random rand, ArrayList<promptFormat> prompts) {
        System.out.println(prompts.get(19));
        int gearChoice = input.nextInt();

        if (gearChoice == 1) {
            int gearRoll = rand.nextInt(2);
            if (gearRoll == 1) {
                System.out.println(prompts.get(20)); 
                System.out.println(prompts.get(12)); 
            } else {
                System.out.println(prompts.get(21)); 
                int bellyRoll = rand.nextInt(2);
                if (bellyRoll == 1) {
                    System.out.println(prompts.get(12)); 
                } else {
                    System.out.println(prompts.get(13)); 
                }
            }
        } else {
            System.out.println(prompts.get(21)); 
            int bellyRoll = rand.nextInt(2);
            if (bellyRoll == 1) {
                System.out.println(prompts.get(12));
            } else {
                System.out.println(prompts.get(13)); 
            }
        }
    }
}

// -----------------------------------------------------

//create flightpath variables
class flightPath {

    private String company, aircraft, startPoint, endPoint;
    private int miles, elapsed;
    private String flightSum;

    public flightPath(String inCompany, String inAircraft, String inStart,
                      String inEnd, int inMiles, int inElapsed) {
        company    = inCompany;
        aircraft   = inAircraft;
        startPoint = inStart;
        endPoint   = inEnd;
        miles      = inMiles;
        elapsed    = inElapsed;
        this.flightSum = "\nToday you will be flying a " + company + " " + aircraft
                + " from " + startPoint + " to " + endPoint
                + ". The trip will cover " + miles
                + " miles and take an estimated " + elapsed + " minutes.";
    }

    @Override
    public String toString() {
        return flightSum;
    }
}

// -----------------------------------------------------

//format strings for promtpts
class promptFormat {

    private String textBody;
    private String[] newText;

    public promptFormat(String inText) {
        textBody = inText;
        newText  = textBody.split("\\~");
    }

    @Override
    public String toString() {
        return String.join("\n", newText);
    }
}
