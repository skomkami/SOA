package pl.edu.agh.model;

import java.io.Serializable;

public class Seat implements Serializable {

    private int number;
    private int price;
    private boolean occupied = false;

    public Seat(int number, int price) {
        this.number = number;
        this.price = price;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
