class Vehicle {
    public double speed() {
        return 0;
    }
}

class Car extends Vehicle {
    private double engineSize;
    private double fuelCapacity;

    Car() {
    }

    Car(double engineSize, double fuelCapacity) {
        this.engineSize = engineSize;
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public double speed() {
        return engineSize * fuelCapacity * 0.5;
    }
}

class Bike extends Vehicle {
    private double wheelSize;
    private double frameWeight;

    Bike() {
    }

    Bike(double wheelSize, double frameWeight) {
        this.wheelSize = wheelSize;
        this.frameWeight = frameWeight;
    }

    @Override
    public double speed() {
        return frameWeight * wheelSize * 10;
    }
}

class Boat extends Vehicle {
    private double hullLength;
    private double displacement;

    Boat() {
    }

    Boat(double hullLength, double displacement) {
        this.hullLength = hullLength;
        this.displacement = displacement;
    }

    @Override
    public double speed() {
        return displacement * hullLength * 3;
    }
}

public class VehicleSpeedTest {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Car(11, 12);
        vehicles[1] = new Bike(21, 22);
        vehicles[2] = new Boat(31, 32);
        for (Vehicle vehical : vehicles) {
            System.out.println(vehical.getClass().getSimpleName() + " Speed= " + vehical.speed());
        }
    }
}