package pl.edu.agh.repos;

import pl.edu.agh.model.Seat;
import java.util.ArrayList;
import java.util.Arrays;


public class SeatsRepository {

    private ArrayList<Seat> seats = new ArrayList<>();

    public SeatsRepository() {
        seats.addAll(
                Arrays.asList(
                        new Seat(1, 100),
                        new Seat(2, 120),
                        new Seat(3, 110),
                        new Seat(4, 90, true),
                        new Seat(5, 50)
                )
        );

    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}
