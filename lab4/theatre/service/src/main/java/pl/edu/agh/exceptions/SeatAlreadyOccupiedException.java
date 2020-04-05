package pl.edu.agh.exceptions;

public class SeatAlreadyOccupiedException extends Exception {

    public SeatAlreadyOccupiedException() {
        super("Seat is  already occupied. Find another one.");
    }
}
