package agh.edu.pl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RequestScoped
@ManagedBean(name = "usedcarlot")
public class UsedCarLot {
    private String mark;
    private String model;
    private String fuelType;

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    private int minPrice;
    private int maxPrice;

    private ArrayList<String> models;

    private HashMap<String, ArrayList<String>> marksAndModels;

    public UsedCarLot(){
//        marks = new ArrayList<>();
//
//        marks.add("Audi");
//        marks.add("Bentley");
//        marks.add("Chevrolet");
//        marks.add("Dodge");
//        marks.add("Maserati");
//        marks.add("Pontiac");

        marksAndModels = new HashMap<>();
        marksAndModels.put("Audi", new ArrayList(Arrays.asList(new String[]{"A6", "A8", "Q7"})));
        marksAndModels.put("Bentley", new ArrayList(Arrays.asList(new String[]{"Continental", "Bentayga"})));
        marksAndModels.put("Dodge", new ArrayList(Arrays.asList(new String[]{"Challenger"})));
    }

    public ArrayList<String> getModels() {
        return models;
    }

    public void update() {
        this.models = marksAndModels.getOrDefault(mark, new ArrayList<>(Arrays.asList("-")));
    }

    public ArrayList<String> getMarks() {
        ArrayList<String> list = new ArrayList<>();

        // Add each element of iterator to the List
        marksAndModels.keySet().iterator().forEachRemaining(list::add);
        return list;
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
}
