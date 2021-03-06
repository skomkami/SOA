package pl.edu.agh;

import pl.edu.agh.exceptions.NotEnoughFundsException;
import pl.edu.agh.exceptions.SeatAlreadyOccupiedException;
import pl.edu.agh.exceptions.SeatNotFoundException;
import pl.edu.agh.model.Seat;
import pl.edu.agh.model.User;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface ISeatsManagerRemote {
    ArrayList<Seat> getSeatList();

    int getSeatPrice(int seatNumber) throws SeatNotFoundException;

    User userWithCredentials(String login, String password);

    Integer getUserBalance();

    void buyTicket(int seatNumber) throws SeatNotFoundException, SeatAlreadyOccupiedException, NotEnoughFundsException;

    void setUserForTransaction(User userForTransaction);
}

