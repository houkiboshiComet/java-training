package jpl.ch02.ex09;

public class Vehicle {
    private double speed; /* km/h */
    private double direction; /* degree clockwise */
    private String owner;
    private String name;
    private static long nextID = 0;
    private final long carID;

    public Vehicle() {
        speed = 0.0;
        direction = 0.0;
        owner = null;
        carID = nextID++;
    }
    public Vehicle(String oenwer) {
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
    public static long getMaxID() {
        if( nextID == 0) {
            return 0;
        } else {
            return nextID - 1;
        }
    }
    public void printVehicleInfo() {
        System.out.println("Vehicle name: " + name );
        System.out.println("owner name: " + owner );
        System.out.println("speed(km/h) " + speed + " km/h" );
        System.out.println("direction: " + direction + " degree (clockwise)" );
        System.out.println("carID: " + carID );
    }
}
