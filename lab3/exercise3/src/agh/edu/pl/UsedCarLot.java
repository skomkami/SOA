package agh.edu.pl;

import agh.edu.pl.model.Car;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestScoped
@ManagedBean(name = "usedcarlot")
public class UsedCarLot implements Serializable {
    private String mark;
    private String model;
    private Car.FuelType fuelType;

    public void setFuelType(Car.FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Car.FuelType getFuelType() {
        return fuelType;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public UsedCarLot() {
        this.models = Car.cars.stream().map(car -> car.model).distinct().collect(Collectors.toCollection(ArrayList::new));
        models.add("");
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    private Integer minPrice;
    private Integer maxPrice;

    private ArrayList<String> models;

    public ArrayList<String> getModels() {
        return models;
    }

    public void update() {
        this.models = Car.cars
                .stream()
                .filter(car -> car.mark.equals(this.mark))
                .map(car -> car.model)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));

        setModel(models.get(0));
    }

    public ArrayList<String> getMarks() {
        return Car.cars.stream().map(car -> car.mark).distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public Car.FuelType[] getFuelTypes() {
        return Car.FuelType.values();
    }

    public String result() {
        return "results";
    }

    public ArrayList<Car> getResults() {
        Stream<Car> cars = Car.cars
                .stream()
                .filter(car -> car.mark.equals(this.mark))
                .filter(car -> car.model.equals(this.model));

        if(this.minPrice != null)
            cars = cars.filter(car -> car.price >= this.minPrice);
        if(this.maxPrice != null)
            cars = cars.filter(car -> car.price <= this.maxPrice);

        return cars.collect(Collectors.toCollection(ArrayList::new));
    }
}
