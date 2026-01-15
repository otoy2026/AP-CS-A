/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.classrelationships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author OToy2026
 */
public class ClassRelationships {

    public static void main(String[] args) {
        //1-5 scale, 1 is most complex/highest priority
        List<Task>ToDo = new ArrayList<>();
        ToDo.add(new Task("Do homework", 2, 3));
        ToDo.add(new Task("Wash dishes", 3, 4));
        ToDo.add(new Task("Watch TV", 5, 5));
        ToDo.add(new Task("Do taxes", 1, 2));
        ToDo.add(new Task("Eat dinner", 4, 1));
        
        Collections.sort(ToDo);
        System.out.println(ToDo);
    }
}

interface Priority{
    public int getPriority();
}

interface Complexity{
    public int getComplexity();
}

class Task implements Comparable<Task>, Priority, Complexity{
    private String name;
    private int complexity, priority;
    
    public Task(String name, int complexity, int priority){
        this.name = name;
        this.complexity = complexity;
        this.priority = priority;
    }
    
    public String getName(){
        return name;
    }
    @Override
    public int getComplexity(){
        return complexity;
    }
    @Override
    public int getPriority(){
        return priority;
    }
   
    //--------------------------------------
    
    @Override
    public int compareTo(Task otherTask){
        if(this.priority != otherTask.getPriority()){
            return Integer.compare(this.priority, otherTask.getPriority());
        }else{
            return Integer.compare(this.complexity, otherTask.getComplexity());
        }
        
    }
    @Override
    public String toString(){
        return name + "(" + priority + ")";
    }
    
    //--------------------------------------
}