package pl.edu.agh.api;

import pl.edu.agh.exceptions.NotEnoughFundsException;
import pl.edu.agh.exceptions.SeatAlreadyOccupiedException;
import pl.edu.agh.exceptions.SeatNotFoundException;
import pl.edu.agh.model.Seat;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface ISeatsManagerRemote {
    ArrayList<Seat> getSeatList();

    int getSeatPrice(int seatNumber) throws SeatNotFoundException;

    void buyTicket(int seatNumber) throws SeatNotFoundException, SeatAlreadyOccupiedException, NotEnoughFundsException;
}

