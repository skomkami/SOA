package pl.edu.agh.api;

import pl.edu.agh.exceptions.SeatNotFoundException;
import pl.edu.agh.model.Seat;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface ISeatsServiceRemote {

    ArrayList<Seat> getSeats();

    Seat getSeatById(int number) throws SeatNotFoundException;
}
