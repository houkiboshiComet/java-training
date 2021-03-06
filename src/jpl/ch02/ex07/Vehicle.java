package jpl.ch02.ex07;

public class Vehicle {

    /* TODO private */
    public double speed; /* km/h */
    public double direction; /* degree clockwise */
    public String owner;
    public String name;
    public static long nextID = 0;
    public final long vehicleID;

    public static void main(String[] args){

        Vehicle myBike = new Vehicle("mike");
        Vehicle speedCar = new Vehicle("lenna");
        Vehicle noname = new Vehicle(null);

        myBike.registerName("my bike");
        //myBike.registerOwner("mike");

        speedCar.registerName("speed car");
        //speedCar.registerOwner("lenna");
        speedCar.reflectSpeed(100.0);
        speedCar.reflectDirection(0.1);

        myBike.printVehicleInfo();
        speedCar.printVehicleInfo();
        noname.printVehicleInfo();
    }

    public Vehicle() {
        speed = 0.0;
        direction = 0.0;
        owner = null;
        vehicleID = nextID++;
    }
    public Vehicle(String owner) {
        this();
        registerOwner(owner);
    }

    public void registerName(String name) {
        this.name = name;
    }
    public void registerOwner(String owner) {
        this.owner = owner;
    }
    public void reflectSpeed(double speed) {
        this.speed = speed;
    }
    public void reflectDirection(double direction) {
        this.direction = direction;
    }

    public void printVehicleInfo() {
        System.out.println("Vehicle name: " + name );
        System.out.println("owner name: " + owner );
        System.out.println("speed(km/h) " + speed + " km/h" );
        System.out.println("direction: " + direction + " degree (clockwise)" );
        System.out.println("vehicleID: " + vehicleID );
    }
    /* BAD HACK FOR TEST */
    public static void resetStaticField() {
        nextID = 0;
    }
}
