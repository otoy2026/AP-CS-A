/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package antreadwritetext;

/**
 *
 * @author OToy2026
 */
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class AntReadWriteText {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Path path = Paths.get("Contacts", "info.txt");
        ArrayList<Contact> contactList = new ArrayList<>();

        //read file into arraylist
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    if (!line.isBlank()) {
                        String[] parts = line.split(",");
                        if (parts.length == 4) {
                            contactList.add(new Contact(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Could not read file: " + e.getMessage());
            }
        }

        while (true) {
            System.out.println("\nOptions: ");
            System.out.println("a: Add contact \ne: Sort by email \ny: Sort by grad year \nn: Sort by name \nq: Quit");
            System.out.print("Selection: ");
            String choice = scan.nextLine().trim().toLowerCase();

            if (choice.equals("q")) break;

            switch (choice) {
                //add contacts
                case "a":
                    System.out.print("Name: "); String name = scan.nextLine();
                    System.out.print("Email: "); String email = scan.nextLine();
                    System.out.print("Grad Year: "); String year = scan.nextLine();
                    System.out.print("Username: "); String user = scan.nextLine();
                    
                    Contact newContact = new Contact(name, email, year, user);
                    contactList.add(newContact);
                    saveToFile(path, contactList);
                    break;
                //sort by email
                case "e":
                    contactList.sort(Comparator.comparing(c -> c.email));
                    break;
                //sotr by graduation year
                case "y":
                    contactList.sort(Comparator.comparing(c -> c.gradYear));
                    break;
                //sort by name
                case "n":
                    contactList.sort(Comparator.comparing(c -> c.name));
                    break;
                default:
                    System.out.println("Invalid option.");
                    continue;
            }
            //print total list
            System.out.println("\nSorted Contacts: ");
            contactList.forEach(System.out::println);
        }
    }

    //overwrite file with new list
    private static void saveToFile(Path path, ArrayList<Contact> list) {
        try {
            Files.createDirectories(path.getParent());
            List<String> lines = new ArrayList<>();
            for (Contact c : list) lines.add(c.toCSV());
            Files.write(path, lines);
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }
public static class Contact {
    String name, email, gradYear, username;

    public Contact(String name, String email, String gradYear, String username) {
        this.name = name;
        this.email = email;
        this.gradYear = gradYear;
        this.username = username;
    }

    // This tells Java how to print the contact
    @Override
    public String toString() {
        return String.format("%-15s , %-20s , %-6s , %-15s", name, email, gradYear, username);
    }

    // Converts object back to CSV format for saving
    public String toCSV() {
        return name + "," + email + "," + gradYear + "," + username;
    }
    }
}