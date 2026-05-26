/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package flightsim;

/**
 *
 * @author OToy2026
 */

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.sound.sampled.*;

public class FlightSim {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("""
                      =====================================
                           Welcome to Flight Simulator
                          =============================""");

        //load flight list
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

        //ask for player name
        System.out.print("Enter your name: ");
        String playerName = input.next();

        //set score
        int score = 0;

        //atc check (pre takeoff)
        System.out.println(prompts.get(0));
        int choice1 = input.nextInt();
        boolean verifiedWithATC;
        if (choice1 == 1) {
            System.out.println(prompts.get(1));
            verifiedWithATC = true;
            score += 10; 
        } else {
            System.out.println(prompts.get(2));
            verifiedWithATC = false;
            score -= 5; 
        }

        //weather event
        int rand1 = rand.nextInt(3);
        boolean survived = false;

        if (rand1 == 1) {

            //clear weather, bird strike
            System.out.println(prompts.get(3));
            int choice2 = input.nextInt();

            if (choice2 == 1) {
                //glide option
                score += 10;
                System.out.println(prompts.get(5));
                int choice3 = input.nextInt();

                if (choice3 == 1) {
                    //attempt to fix radio
                    score += 5;
                    System.out.println(prompts.get(6));
                    int answer = input.nextInt();

                    if (answer == 132) {
                        score += 20; 
                        System.out.println(prompts.get(7));
                        System.out.println(prompts.get(10));
                        survived = attemptLanding(input, rand, prompts, score);
                        score = updateLandingScore(score, survived);
                    } else {
                        score -= 10; 
                        System.out.println(prompts.get(8));
                        System.out.println(prompts.get(11));
                        int choice4 = input.nextInt();
                        if (choice4 == 1) {
                            score += 5;
                            survived = attemptLanding(input, rand, prompts, score);
                            score = updateLandingScore(score, survived);
                        } else {
                            System.out.println(prompts.get(14));
                            score -= 20;
                        }
                    }

                } else {
                    //no radio attempt
                    score -= 5;
                    System.out.println(prompts.get(9));
                    System.out.println(prompts.get(11));
                    int choice4 = input.nextInt();
                    if (choice4 == 1) {
                        score += 5;
                        survived = attemptLanding(input, rand, prompts, score);
                        score = updateLandingScore(score, survived);
                    } else {
                        System.out.println(prompts.get(14));
                        score -= 20;
                    }
                }

            } else if (choice2 == 2) {
                //full throttle option
                score -= 10;
                System.out.println(prompts.get(15));
                int choice3 = input.nextInt();
                if (choice3 == 1) {
                    score += 5;
                    survived = attemptLanding(input, rand, prompts, score);
                    score = updateLandingScore(score, survived);
                } else {
                    System.out.println(prompts.get(13));
                    score -= 20;
                }

            } else {
                System.out.println("Invalid input.");
            }

        } else if (rand1 == 0) {

            //stormy weather, flap loose
            System.out.println(prompts.get(4));
            int choice2 = input.nextInt();

            if (choice2 == 1) {
                //call atc option
                score += 10;
                if (verifiedWithATC) {
                    score += 10;  
                    System.out.println(prompts.get(16));
                } else {
                    System.out.println(prompts.get(17));
                }
                survived = attemptLanding(input, rand, prompts, score);
                score = updateLandingScore(score, survived);

            } else if (choice2 == 2) {
                //dont report option
                score -= 10;
                System.out.println(prompts.get(9));
                System.out.println(prompts.get(18));
                int choice3 = input.nextInt();
                if (choice3 == 1) {
                    score += 5;
                    survived = attemptLanding(input, rand, prompts, score);
                    score = updateLandingScore(score, survived);
                } else {
                    System.out.println(prompts.get(13));
                    score -= 20;
                }

            } else {
                System.out.println("Invalid input.");
            }

        } else {

            //fuel warning
            System.out.println(prompts.get(22));
            int choice2 = input.nextInt();

            if (choice2 == 1) {
                //divert option
                score += 10;
                System.out.println(prompts.get(23));
                survived = attemptLanding(input, rand, prompts, score);
                score = updateLandingScore(score, survived);

            } else if (choice2 == 2) {
                //calculation option
                score += 5;
                System.out.println(prompts.get(24));
                int answer = input.nextInt();
                if (answer == 56) {
                    score += 20; 
                    System.out.println(prompts.get(25));
                    survived = attemptLanding(input, rand, prompts, score);
                    score = updateLandingScore(score, survived);
                } else {
                    score -= 20; 
                    System.out.println(prompts.get(26));
                }

            } else {
                System.out.println("Invalid input.");
            }
        }

        //save and display scores
        saveScore(playerName, score);
        displayLeaderboard();
        Thread.sleep(2000); // <-- added: give audio time to finish
    }

    //landing gear check
    //returns true if the player survive, false if they crash
    static boolean attemptLanding(Scanner input, Random rand,
                                  ArrayList<promptFormat> prompts, int score) {
        System.out.println(prompts.get(19));
        int gearChoice = input.nextInt();

        if (gearChoice == 1) {
            int gearRoll = rand.nextInt(2);
            if (gearRoll == 1) {
                System.out.println(prompts.get(20));
                System.out.println(prompts.get(12));
                return true;
            } else {
                System.out.println(prompts.get(21));
                int bellyRoll = rand.nextInt(2);
                if (bellyRoll == 1) {
                    System.out.println(prompts.get(12));
                    return true;
                } else {
                    System.out.println(prompts.get(13));
                    return false;
                }
            }
        } else {
            System.out.println(prompts.get(21));
            int bellyRoll = rand.nextInt(2);
            if (bellyRoll == 1) {
                System.out.println(prompts.get(12));
                return true;
            } else {
                System.out.println(prompts.get(13));
                return false;
            }
        }
    }

    //landing bonus to score
    static int updateLandingScore(int score, boolean survived) {
        playSound(survived);
        if (survived) {
            return score + 30;   // survival bonus
        } else {
            return score - 30;   // crash penalty
        }
    }

    //add to scores.txt
    static void saveScore(String name, int score) {
        Path scoresPath = Paths.get("Info", "scores.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(
                scoresPath,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(name + "," + score);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Could not save score: " + e.getMessage());
        }
    }

    //read scores and display top 5
    static void displayLeaderboard() {
        Path scoresPath = Paths.get("Info", "scores.txt");
        ArrayList<String[]> scores = new ArrayList<>();

        if (Files.exists(scoresPath)) {
            try (Scanner scan3 = new Scanner(scoresPath)) {
                while (scan3.hasNextLine()) {
                    String line = scan3.nextLine().trim();
                    if (!line.isEmpty()) {
                        String[] parts = line.split(",");
                        if (parts.length == 2) {
                            scores.add(parts);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Could not read scores: " + e.getMessage());
                return;
            }
        }

        //sort by score
        scores.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

        System.out.println("\n=== LEADERBOARD (Top 5) ===");
        int display = Math.min(5, scores.size());
        for (int i = 0; i < display; i++) {
            System.out.println((i + 1) + ". " + scores.get(i)[0]
                    + " - " + scores.get(i)[1] + " pts");
        }
        System.out.println("===========================");
       
    }
    
static void playSound(boolean success) {
    String fileName = success ? "success.wav" : "crash.wav";
    Path soundPath = Paths.get("Info", fileName);

    try {
        AudioInputStream audio = AudioSystem.getAudioInputStream(soundPath.toFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
        Thread.sleep(clip.getMicrosecondLength() / 1000);
        clip.close();
        audio.close();
    } catch (Exception e) {
        System.out.println("Sound error: " + e.getMessage());
    }
}
}

// ----------------------------------------------

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

// ----------------------------------------------

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

