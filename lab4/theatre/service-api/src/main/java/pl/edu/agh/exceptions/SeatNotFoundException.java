package pl.edu.agh.exceptions;

public class SeatNotFoundException extends Exception {
    public SeatNotFoundException(int i) {
        super("Seat with number " + i + " not found");
    }
}
