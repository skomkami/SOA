package agh.edu.pl.model;

import java.util.ArrayList;

public class Car {
    public String mark;
    public String model;
    public FuelType fuelType;
    public int price;

    public Car(String mark, String model, FuelType fuelType, int price) {
        this.mark = mark;
        this.model = model;
        this.fuelType = fuelType;
        this.price = price;
    }

    public static ArrayList<Car> cars;
    static
    {
        cars = new ArrayList<>();
        cars.add(new Car("Audi", "A6", FuelType.GASOLINE, 25000));
        cars.add(new Car("Audi", "A6", FuelType.DIESEL,  5000));
        cars.add(new Car("Audi", "A6", FuelType.GASOLINE,  100000));
        cars.add(new Car("Audi", "A8", FuelType.DIESEL,  20000));
        cars.add(new Car("Audi", "A8", FuelType.GASOLINE,  50000));
        cars.add(new Car("Audi", "A8", FuelType.GASOLINE,  200000));
        cars.add(new Car("Audi", "Q7", FuelType.DIESEL,  100000));
        cars.add(new Car("Audi", "Q7", FuelType.DIESEL,  80000));

        cars.add(new Car("Bentley", "Continental", FuelType.GASOLINE, 400000));
        cars.add(new Car("Bentley", "Continental", FuelType.GASOLINE,  250000));
        cars.add(new Car("Bentley", "Continental", FuelType.GASOLINE,  800000));
        cars.add(new Car("Bentley", "Bentayga", FuelType.GASOLINE, 800000));
        cars.add(new Car("Bentley", "Bentayga", FuelType.GASOLINE, 250000));
        cars.add(new Car("Bentley", "Bentayga", FuelType.GASOLINE, 500000));
        cars.add(new Car("Bentley", "Bentayga", FuelType.GASOLINE, 400000));

        cars.add(new Car("Dodge", "Challenger", FuelType.GASOLINE, 40000));
        cars.add(new Car("Dodge", "Challenger", FuelType.GASOLINE, 80000));
        cars.add(new Car("Dodge", "Challenger", FuelType.GASOLINE, 60000));
        cars.add(new Car("Dodge", "Viper", FuelType.GASOLINE, 60000));
        cars.add(new Car("Dodge", "Viper", FuelType.GASOLINE, 60000));
        cars.add(new Car("Dodge", "Viper", FuelType.GASOLINE, 60000));
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public int getPrice() {
        return price;
    }

    public enum FuelType {
        DIESEL,
        GASOLINE
    }
}
