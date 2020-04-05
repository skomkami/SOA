package pl.edu.agh.api;

import pl.edu.agh.exceptions.SeatNotFoundException;
import pl.edu.agh.model.Seat;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface ISeatsService {

    ArrayList<Seat> getSeats();

    Seat getSeatById(int number) throws SeatNotFoundException;
}
