/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmain;

/**
 *
 * @author OToy2026
 */

// PLANNING:
// This project has 3 classes: StudentMain, Student, and Course.
// StudentMain uses the Student class to create a student object 
// The Student class uses the Course class to set test scores
// Student class also uses Course class to get and do calculations with test scores

//public class
public class StudentMain{
    public static void main(String args[]){
        Student bob = new Student("Bob", "Smith");
        //create student object
        
        System.out.println(bob);
    }
}
//basic class Student - should have 3 courses
class Student
{
    private String firstName, lastName; 
   
    //create courses and set scores for each
    Course cl1 = new Course();    
    int s1 = cl1.setScore(85);
    
    Course cl2 = new Course();
    int s2 = cl2.setScore(78);
    
    Course cl3 = new Course();
    int s3 = cl3.setScore(96);
    
    //empty constructor to create empty Student object
    public Student()
    {
        firstName = "";
        lastName = "";
    }
    //basic constructor to create Student object with name only
    public Student(String first, String last)
    {
        firstName = first;
        lastName = last;
    }
    //constructor for 3 Course objects
    public Student(Course course1, Course course2, Course course3){
        cl1 = course1;
        cl2 = course2;
        cl3 = course3;
    }
    //calculating average between 3 scores
    public int getAverage(){
        int net = cl1.getScore()+cl2.getScore()+cl3.getScore();
        int average = (net/3);
        return average;
    }
    
    //basic toString code
    public String toString()
    {
        String result;
        result = firstName + " " + lastName + "\n"+ "Average Score: "+ getAverage();
        return result;
    }
}
//basic class Course
class Course{
    public int score = 0;
    
    public int setScore(int inScore){
        score = inScore;
        return score;
    }
    
    public int getScore(){
        return score;
    }
}
