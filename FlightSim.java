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

        //welcome message
        System.out.println("===========================================");
        System.out.println("       WELCOME TO FLIGHT SIMULATOR         ");
        System.out.println("===========================================");

        //create list of flights
        Path path1 = Paths.get("Info", "flights.txt");
        ArrayList<FlightPath> flights = new ArrayList<>();

        if (Files.exists(path1)) {
            try (Scanner scan1 = new Scanner(path1)) {
                while (scan1.hasNextLine()) {
                    String line = scan1.nextLine().trim();
                    if (line.isEmpty()) continue;
                    String[] parts = line.split(",");
                    //add flight paths
                    flights.add(new FlightPath(
                        parts[0], parts[1], parts[2],
                        parts[3],
                        Integer.parseInt(parts[4]),
                        Integer.parseInt(parts[5])
                    ));
                }
            } catch (IOException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }

        if (flights.isEmpty()) {
            System.err.println("No flight data found. Exiting.");
            return;
        }

        Random rand = new Random();
        FlightPath todaysFlight = flights.get(rand.nextInt(flights.size()));
        System.out.println(todaysFlight);

        //create prompts list
        Path path2 = Paths.get("Info", "prompts.txt");
        ArrayList<PromptFormat> prompts = new ArrayList<>();

        if (Files.exists(path2)) {
            try (Scanner scan2 = new Scanner(path2)) {
                scan2.useDelimiter("-");
                while (scan2.hasNext()) {
                    String content = scan2.next().trim();
                    if (!content.isEmpty()) {
                        prompts.add(new PromptFormat(content));
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading prompts: " + e.getMessage());
            }
        }

        //game objects
        FlightState state   = new FlightState(todaysFlight);
        AircraftHealth ac   = new AircraftHealth();
        NavStatus nav       = new NavStatus();
        Scanner input       = new Scanner(System.in);

        //being phase1: pre-takeoff
        System.out.println(prompts.get(0));
        int choice1 = safeIntInput(input);

        if (choice1 == 1) {
            System.out.println(prompts.get(1));
            nav.atcContactEstablished = true;
        } else {
            System.out.println(prompts.get(2));
        }

        //begin phase2: weather
        int weatherRoll = rand.nextInt(2);

        if (weatherRoll == 1) {
            //clear weather, bird strike, lost engine
            System.out.println(prompts.get(3));
            ac.sys.rightEngineFail();
            state.addEvent("Right engine lost to bird strike.");

            int choice2 = safeIntInput(input);

            if (choice2 == 1) {
                //glide, lost radio, puzzle
                System.out.println(prompts.get(5));
                int choice3 = safeIntInput(input);

                if (choice3 == 1) {
                    //try radio
                    System.out.println(prompts.get(6));
                    int answer = safeIntInput(input);

                    if (answer == 132) {
                        System.out.println(prompts.get(7));
                        nav.atcContactEstablished = true;
                        state.addEvent("Radio fixed. ATC contacted.");

                        System.out.println(prompts.get(10));
                        int choice4 = safeIntInput(input);

                        if (choice4 == 1) {
                            System.out.println(prompts.get(11));
                            int landingRoll = rand.nextInt(2);
                            if (landingRoll == 1 || !ac.sys.rightEngine) {
                                System.out.println(prompts.get(12));
                                state.land = true;
                            } else {
                                System.out.println(prompts.get(13)); 
                                ac.body.fuselageHealth -= 40;
                                state.land = true;
                            }
                        } else {
                            System.out.println(prompts.get(14)); 
                            phase3ContinueFlight(input, rand, state, ac, nav, prompts);
                        }

                    } else {
                        System.out.println(prompts.get(8));
                        state.addEvent("Radio repair failed. Flying blind.");
                        phase3NoCommunications(input, rand, state, ac, prompts);
                    }

                } else {
                    System.out.println(prompts.get(9)); 
                    phase3NoCommunications(input, rand, state, ac, prompts);
                }

            } else if (choice2 == 2) {
                System.out.println(prompts.get(15)); 
                ac.sys.leftEngineFail();
                state.addEvent("Left engine also failed from over-throttle.");
                endingCrash(state, ac);

            } else {
                System.out.println("Invalid input.");
            }

        } else {
            //stormy weather, flap damaged
            System.out.println(prompts.get(4));
            ac.body.rightFlapHealth = 30;
            state.addEvent("Right wing flap partially detached.");

            int choice2 = safeIntInput(input);

            if (choice2 == 1) {
                if (nav.atcContactEstablished) {
                    System.out.println(prompts.get(16)); 
                    nav.diversionApproved = true;
                } else {
                    System.out.println(prompts.get(17));
                    System.out.println("Enter the squawk code ATC just gave you (type 7700): ");
                    int squawk = safeIntInput(input);
                    if (squawk == 7700) {
                        System.out.println(prompts.get(18));  
                        nav.diversionApproved = true;
                    } else {
                        System.out.println(prompts.get(19)); 
                    }
                }

                if (nav.diversionApproved) {
                    System.out.println(prompts.get(20));  
                    int choice3 = safeIntInput(input);
                    if (choice3 == 1) {
                        System.out.println(prompts.get(21));  
                        state.land = true;
                    } else {
                        System.out.println(prompts.get(22));
                        ac.body.rightFlapHealth = 0;
                        state.addEvent("Right flap fully detached mid-flight.");
                        phase3NoCommunications(input, rand, state, ac, prompts);
                    }
                } else {
                    phase3NoCommunications(input, rand, state, ac, prompts);
                }

            } else if (choice2 == 2) {
                System.out.println(prompts.get(9)); 
                ac.body.rightFlapHealth = 0;
                state.addEvent("Right flap fully detached. Aircraft destabilized.");

                //emergecy descenct
                System.out.println(prompts.get(23));
                int choice3 = safeIntInput(input);
                if (choice3 == 1) {
                    int roll = rand.nextInt(3);
                    if (roll < 2) {
                        System.out.println(prompts.get(24));  
                        state.land = true;
                    } else {
                        System.out.println(prompts.get(13));  
                        ac.body.fuselageHealth -= 50;
                        state.land = true;
                    }
                } else {
                    System.out.println(prompts.get(25));  
                    endingCrash(state, ac);
                }

            } else {
                System.out.println("Invalid input.");
            }
        }
        if (!state.crash) {
            endingSuccess(state, ac);
        }
    }

    static void phase3NoCommunications(Scanner input, Random rand,
            FlightState state, AircraftHealth ac, ArrayList<PromptFormat> prompts) {

        System.out.println(prompts.get(26)); 
        int choice = safeIntInput(input);

        if (choice == 1) {
            System.out.println(prompts.get(27));
            if (ac.sys.rightEngine) {
                System.out.println(prompts.get(28)); 
                state.land = true;
            } else {
                System.out.println(prompts.get(29));  
                int roll = rand.nextInt(2);
                if (roll == 1) {
                    System.out.println(prompts.get(24));  
                    state.land = true;
                } else {
                    endingCrash(state, ac);
                }
            }
        } else if (choice == 2) {
            System.out.println(prompts.get(30)); 
            ac.body.fuselageHealth -= 20;
            state.land = true;
        } else {
            System.out.println("Invalid input.");
            state.land = true;
        }
    }

    static void phase3ContinueFlight(Scanner input, Random rand,
            FlightState state, AircraftHealth ac, NavStatus nav,
            ArrayList<PromptFormat> prompts) {

        System.out.println(prompts.get(31)); 
        int choice = safeIntInput(input);

        if (choice == 1) {
            System.out.println(prompts.get(32));  
            int answer = safeIntInput(input);
            if (answer == 45) {
                System.out.println(prompts.get(33));  
                state.land = true;
            } else {
                System.out.println(prompts.get(34));  
                nav.diversionApproved = true;
                System.out.println(prompts.get(21)); 
                state.land = true;
            }
        } else {
            int roll = rand.nextInt(2);
            if (roll == 1) {
                System.out.println(prompts.get(35));  
                state.land = true;
            } else {
                System.out.println(prompts.get(36)); 
                endingCrash(state, ac);
            }
        }
    }

    static void endingCrash(FlightState state, AircraftHealth ac) {
        state.crash = true;
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         FLIGHT ENDED IN CRASH         ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println(state.getFlightLog());
    }

    static void endingSuccess(FlightState state, AircraftHealth ac) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       FLIGHT COMPLETED SAFELY         ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("Aircraft structural integrity: " + ac.body.fuselageHealth + "%");
        System.out.println(state.getFlightLog());
    }

    static int safeIntInput(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        return sc.nextInt();
    }
}

// --------------------------------------------------------------------

class FlightPath {

    private final String company, aircraft, startPoint, endPoint;
    private final int miles, elapsed;

    public FlightPath(String company, String aircraft, String start,
                      String end, int miles, int elapsed) {
        this.company    = company;
        this.aircraft   = aircraft;
        this.startPoint = start;
        this.endPoint   = end;
        this.miles      = miles;
        this.elapsed    = elapsed;
    }

    public String getAircraft() { return aircraft; }
    public int getMiles()       { return miles; }

    @Override
    public String toString() {
        return "\nToday you will be flying a " + company + " " + aircraft
             + " from " + startPoint + " to " + endPoint
             + ".\nThe trip will cover " + miles
             + " miles and take an estimated " + elapsed + " minutes.\n";
    }
}

// --------------------------------------------------------------------

class PromptFormat {

    private final String[] lines;

    public PromptFormat(String inText) {
        lines = inText.split("~");
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }
}

// --------------------------------------------------------------------

//flight progress tracking
class FlightState {

    public boolean land  = false;
    public boolean crash = false;

    private final FlightPath flight;
    private final List<String> eventLog = new ArrayList<>();

    public FlightState(FlightPath flight) {
        this.flight = flight;
        addEvent("Departed on " + flight.toString().trim());
    }

    public void addEvent(String event) {
        eventLog.add(event);
    }

    public String getFlightLog() {
        StringBuilder sb = new StringBuilder("\n── Flight Log ──\n");
        for (int i = 0; i < eventLog.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(eventLog.get(i)).append("\n");
        }
        return sb.toString();
    }
}

// --------------------------------------------------------------------

//navigation status
class NavStatus {
    public boolean atcContactEstablished = false;
    public boolean diversionApproved     = false;
    public String  squawkCode            = "1200";  // default VFR squawk
}

// --------------------------------------------------------------------

//health tracking variables
class AircraftHealth {
    public final SysHealth  sys  = new SysHealth();
    public final BodyHealth body = new BodyHealth();

    public boolean isFlightworthy() {
        return (sys.leftEngine || sys.rightEngine)
            && sys.controlsOn
            && body.fuselageHealth > 0;
    }
}

// --------------------------------------------------------------------

class SysHealth {
    public int     sysIntegrity = 100;
    public boolean commsOn      = true;
    public boolean controlsOn   = true;
    public boolean leftEngine   = true;
    public boolean rightEngine  = true;

    public boolean commsFail()        { return commsOn      = false; }
    public boolean controlsFail()     { return controlsOn   = false; }
    public boolean leftEngineFail()   { return leftEngine   = false; }
    public boolean rightEngineFail()  { return rightEngine  = false; }
}

// --------------------------------------------------------------------

class BodyHealth {
    public int fuselageHealth   = 100;
    public int leftWingHealth   = 100;
    public int rightWingHealth  = 100;
    //for stormy branch:
    public int rightFlapHealth  = 100;
}
