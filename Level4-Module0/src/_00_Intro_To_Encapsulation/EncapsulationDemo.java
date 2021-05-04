package _00_Intro_To_Encapsulation;

public class EncapsulationDemo {

    /*
     * Encapsulation is the process of wrapping together related members(methods
     * and variables) under the umbrella of a single class and limiting access
     * from outside classes.
     * 
     * 1.) Read about the Car and SUV classes below.
     * 
     * 2.) Try instantiating objects of each in the main to access their values
     * and methods. If you can't access a value or method, find out why and
     * change it or make a new method that enables you to do so.
     */

    public static void main(String[] args) {

    }

}

class Car {

    /*
     * There are three access modifiers in Java:
     * 
     * public makes the member variable or method accessible from any class
     * within the project that makes an instance of the class.
     * 
     * This is the preferred default for your constructor, getters, setters and
     * any other methods required to use your class.
     */

    public double tankCapacity;

    /*
     * private makes the member variable or method accessible only within the
     * class.
     * 
     * This is the preferred default for most member variables as well as helper
     * methods* that assist the class in performing tasks, but have no outside
     * utility.
     */

    private double fuelInTank;

    /*
     * protected makes the member variable or method accessible only to classes
     * within the same package or the class's subclasses.
     * 
     * It is useful if you want methods and member variables to have limited
     * accessibility for the listed exceptions.
     */

    protected String color;

    /*
     * If you put no access modifier(also called default) The method or variable
     * will have accessibility similar to protected, but cannot be accessed by
     * subclasses outside the current package.
     */

    int mpg;

    public Car(double tankCapacity, double fuelInTank, String color, int mpg) {

        this.tankCapacity = tankCapacity;
        this.fuelInTank = fuelInTank;
        this.color = color;
        this.mpg = mpg;

    }

    /*
     * A getter(also called an accessor) is a method that allows controlled
     * access to private or protected member variables. It usually follows a
     * format similar to the method shown below.
     */

    public double getFuelInTank() {

        return fuelInTank;

    }

    /*
     * A setter(also called a mutator) is a method that allows controlled
     * changes to private or protected member variables. It usually follows a
     * format similar to the method shown below.
     */

    public void setFuelInTank(double fuelInTank) {

        if (fuelInTank > 0 && fuelInTank <= tankCapacity) {

            this.fuelInTank = fuelInTank;

        } else {

            System.out.println("Not a valid amount of fuel.");

        }

    }

    /*
     * Controlling which members variables are accessible and mutable through
     * getters and setters makes your code easier to use and, as shown in the
     * setter above, allows you to screen incoming data that could be
     * problematic.
     */

    // Below is an example of a helper method.

    private boolean fuelInTank() {

        return fuelInTank > 0;

    }

    public double gettankCapacity() {

        return tankCapacity;
    }

    protected void drive() {

        if (fuelInTank()) {
            System.out.println("I'm driving my " + color + "car...");
            setFuelInTank(fuelInTank - 1);
        } else {
            System.out.println("I'm out of gas.");
        }

    }

}

class SUV extends Car {

    private int numSeats;

    public SUV(double tankCapacity, double fuelInTank, String color, int mpg,
            int numSeats) {

        super(tankCapacity, fuelInTank, color, numSeats);

        this.numSeats = numSeats;

    }

    public int getNumSeats() {

        return numSeats;

    }

    public void setNumSeats(int numSeats) {

        this.numSeats = numSeats;

    }

    private void offroad() {

        drive();

        System.out.println("in the dirt.");

    }
}
