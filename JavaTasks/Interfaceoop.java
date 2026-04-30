interface IDrivable {

    void start();
    void stop();
    default void describe(){
        System.out.println("This is drivable vehicle.");
    }
}

abstract class Vehicle implements IDrivable {
    private String brand;
    public Vehicle(String brand) {
        this.brand=brand;
        System.out.println("Vehicle constructor called");
    }

    abstract double calculateFuelEfficiency();

    public abstract String toString();

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }
}

class Car extends Vehicle {
    private double distanceTravelled;
    private double fuelConsumed;

    public Car(String brand, double distanceTravelled, double fuelConsumed) {
        super(brand);
        this.distanceTravelled=distanceTravelled;
        this.fuelConsumed=fuelConsumed;
        System.out.println("Car constructor called");
    }

    public double distanceTravelled(){
        return distanceTravelled;
    }
    public double fuelConsumed(){
        return fuelConsumed;
    }

    @Override
    public double calculateFuelEfficiency() {
        return distanceTravelled/fuelConsumed;
    }

    @Override
    public String toString() {
        return "Car brand is " + getBrand() + "and fuel efficiency is:" + calculateFuelEfficiency() + "km/l";
    }

    public void start(){
        System.out.println("Car "+getBrand()+" engine started.");
    }
    public void stop(){
        System.out.println("Car "+getBrand()+" engine stopped.");
    }
}
class Motorcycle extends Vehicle {
    private int engineCapacity;
    private double mileage;

    public Motorcycle(String brand, int engineCapacity, double mileage){
        super(brand);
        this.engineCapacity=engineCapacity;
        this.mileage=mileage;
        System.out.println("Motorcycle constructor called");
    }

    public int engineCapacity(){
        return engineCapacity;
    }
    public double mileage(){
        return mileage;
    }

    @Override
    public double calculateFuelEfficiency() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Motorcycle brand is " + getBrand() + "and fuel efficiency is:" + calculateFuelEfficiency() + "km/l";
    }

    public void start(){
        System.out.println("Motorcycle "+getBrand()+" engine started.");
    }
    public void stop(){
        System.out.println("Motorcycle "+getBrand()+" engine stopped.");
    }
}

public class Interfaceoop {
    public static void main(String[] args) {
        Vehicle c1 = new Car("Toyota", 500, 40);
        Vehicle m1 = new Motorcycle("Yamaha", 150, 45.5);
        System.out.println(c1.toString());
        System.out.println(m1.toString());
        c1.start();
        c1.stop();
        m1.start();
        m1.stop();
        
        c1.describe();

        c1.setBrand("Honda");
        System.out.println("Updated " + c1.toString());

    }
}