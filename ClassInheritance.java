/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.classinheritance;

/**
 *
 * @author OToy2026
 */
public class ClassInheritance {

    public static void main(String[] args){
        Rav4 myRav4 = new Rav4();
        R1T myR1T = new R1T();
        
        CruiseShip myCruise = new CruiseShip();
        AircraftCarrier myCarrier = new AircraftCarrier();
        
        A380 myA380 = new A380();
        Concorde myConcorde = new Concorde();
        
        ISS myISS = new ISS();
        Gundam myGundam = new Gundam();
        
        System.out.println(myRav4+"\n");
        System.out.println(myR1T+"\n");
        System.out.println(myCruise+"\n");
        System.out.println(myCarrier+"\n");
        System.out.println(myA380+"\n");
        System.out.println(myConcorde+"\n");
        System.out.println(myISS+"\n");
        System.out.println(myGundam+"\n");
    }

}
// The Base Vehicle Class, implements the Speedometer interface
class Vehicle implements Speedometer{
    //base (Class wide) variables
    protected String brandName = "";
    protected double speed = 0.0;
    protected int passengers = 0;
    protected double cargoWeight = 0.0;
    //Base default constructor 
    public Vehicle(){
        brandName = "";
        speed = 0.0;
        passengers = 0;
        cargoWeight = 0.0;
    }

    public Vehicle(String inBrand, double inSpeed, int inPassengers, double inCargo){
        brandName = inBrand;
        speed = inSpeed;
        passengers = inPassengers;
        cargoWeight = inCargo;
    }

    //getters and setters
    public Vehicle(String inBrand){
        brandName = inBrand;
    }

    public String getBrand(){
        return brandName;
    }

    public  void setBrandName(String inBrand){
        brandName = inBrand;
    }

    public double getSpeed(){
        return  speed;
    }

    public void setSpeed(double inSpeed){
        speed = inSpeed;
    }

    public int getPassengers(){
        return  passengers;
    }

    public void setPassengers(int inPassengers){
        speed = inPassengers;
    }

    public double getCargoWeight(){
        return  cargoWeight;
    }

    public void setCargoWeight(double inCargoWeight){
        cargoWeight = inCargoWeight;
    }

    //Base toString
    public String toString(){
        String result = "";
        result = "Brand: \t\t" + getBrand() + "\n" +
                "Speed (mph): \t" + getSpeed() + "\n" +
                "Passengers: \t" + getPassengers() + "\n" +
                "Cargo (lbs): \t" + getCargoWeight() + "\n";
        return result;
    }
}
//the Speedometer interface to show the speed of any vehicle in the same way
interface Speedometer{
    public void setSpeed(double inSpeed);
    public double getSpeed();
 }
// Car Class inherits from Vehicle Class

class Car extends Vehicle{
    int wheels = 4;
    String color = "White";
    boolean spoiler = false;    
    boolean stereo = false;     
    double mpg = 0.0; //has an extra variable, mpg

    public Car(String inBrand, double inSpeed, int inPassengers, double inCargo, double inMPG){
        super(inBrand, inSpeed, inPassengers, inCargo); //uses the super constructor
        mpg = inMPG; //also include the extra variable in the Car constructor
    }
    //another additional variable
    public void setSpoiler(boolean inSpoiler){
        spoiler = inSpoiler;
    }

    public boolean getSpoiler(){
        return spoiler;
    }
    //another additional variable
    public void setStereo(boolean inStereo){
        stereo = inStereo;
    }

    public boolean getStereo(){
        return  stereo;
    }
    //overrides the super getSpeed method
    @Override
    public double getSpeed() {
        if (spoiler)
            return super.getSpeed() + 20;
        else
            return super.getSpeed();
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public double getMPG(){
        if(stereo)
            return mpg - (mpg / 10);
        else
            return mpg;
    }
    //usesd the super toString, as well as addingnthe new variable to it.
    public String toString(){
        String result = super.toString() +
                "MPG :\t\t" + this.getMPG();
        return  result ;
    }
}

class Rav4 extends Car {

    public boolean OffRoadMode;
    
    public Rav4() {
        super("Toyota", 160, 5, 800, 27);  
        OffRoadMode = true;
    }

    @Override
    public String toString() {
        return "Rav4\n" +
               super.toString() +
               "\nOff Roading:\t"+OffRoadMode;
    }
}

class R1T extends Car{
    
    private int batteryRange;
    
    public R1T(){
        super("Rivian", 115, 5, 1760, 82);
        batteryRange = 400;
    }
    
    @Override
    public String toString() {
        return "R1T\n" +
               super.toString() +
               "\nBattery Range:\t"+batteryRange;
    } 
}

class CruiseShip extends Vehicle {

    private int Tonnage;

    public CruiseShip() {
        super("Carnival", 24, 4500, 100000);
        Tonnage = 135000;
    }

    @Override
    public String toString() {
        return "Carnival Cruise Ship\n" +
               super.toString() +
               "Weight:\t\t"+Tonnage+" gross tons";
    }
}

class AircraftCarrier extends Vehicle{
    
    private int NuclearReactors;
    private int AircraftCapacity;
    
    public AircraftCarrier(){
        super("US Navy", 35, 5200, 104000);
        NuclearReactors = 2;
        AircraftCapacity = 64;
    }
    
    @Override
    public String toString(){
        return "Nimitz Aircraft Carriern\n"+
               super.toString()+
               "Aircraft Capacity: "+AircraftCapacity+
               "\nNuclear Reactors: " +NuclearReactors;
    }
}

class A380 extends Vehicle{
    private int decks;
    private int engines;
    
    public A380(){
        super("Airbus", 587, 525, 110000);
        decks = 2;
        engines = 4;
    }
    
    @Override
    public String toString(){
         return "A380\n"+
                 super.toString()+
                 "Number of decks: "+decks+
                 "\nNumber of engines: "+engines;    
    }
}

class Concorde extends Vehicle{
    private boolean inService;
    private boolean supersonic;
    
    public Concorde(){
        super("Sud Aviation / British Aircraft Corporation", 1354, 92, 29500);
        inService = false;
        supersonic = true;
    }
    
    @Override
    public String toString(){
        return "Concorde\n"+
                super.toString()+
                "Supersonic:\t"+supersonic+
                "\nIn service:\t"+inService;
    }
}

class ISS extends Vehicle{
    private int distanceFromEarth;
    private double orbitalPeriod;
    private boolean hasGravity;
    
    public ISS(){
        super("NASA / Roscosmos / ESA / JAXA / CSA", 17100, 7, 11000);
        distanceFromEarth = 254;
        orbitalPeriod = 92.9;
        hasGravity = false;
    }
    
    @Override
    public String toString(){
        return "International Space Station\n"+
                super.toString()+
                "Distance from Earth: "+distanceFromEarth+
                "\nOrbital Period: "+orbitalPeriod+" minutes"+
                "\nGravity:\t\t"+hasGravity;
    }
}

class Gundam extends Vehicle{
    private int legs;
    private int arms;
    private boolean hasWeapons;
    
    public Gundam(){
        super("Universal Century companies", 100, 1, 2000);
        legs = 2;
        arms = 2;
        hasWeapons = true;
    }
    
    @Override
    public String toString(){
        return "Gundam\n"+
                super.toString()+
                "Legs:\t\t"+legs+
                "\nArms:\t\t"+arms+
                "\nWeapons:\t"+hasWeapons;
    }
}