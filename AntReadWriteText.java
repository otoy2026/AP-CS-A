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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AntReadWriteText {
    public static void main(String[] args) {
        //set up scanner
        Scanner scan = new Scanner(System.in);
        
        //ask user for information
        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        
        System.out.println("Enter your email: ");
        String email = scan.nextLine();
        
        System.out.println("Enter your graduation year: ");
        String gradYear = scan.nextLine();
        
        System.out.println("Enter your username: ");
        String username = scan.nextLine();
        
        String safeName = name.replaceAll("[\\\\/:*?\"<>|]", "_");
        String folderName = "Contacts";
        String fileName = safeName + ".txt";
        Path path = Paths.get(folderName, fileName);

        //create new concatenated string
        String content = name+", "+email+", "+gradYear+", "+username;

        try {
            //get directory
            Files.createDirectories(path.getParent());

            //write new file
            Files.write(path, content.getBytes());
            System.out.println("Information saved to: " + path.toAbsolutePath());

            //read file
            System.out.println("\nContact Information:");
            String readContent = new String(Files.readAllBytes(path));
            System.out.println(readContent);

        } catch (IOException e) {
            //find error (if error exists)
            System.err.println("Error details: " + e.getMessage());
            e.printStackTrace();
        }
    }
}