package pl.edu.agh.impl;

import pl.edu.agh.ISeatsManagerRemote;
import pl.edu.agh.ISeatsService;
import pl.edu.agh.ITicketsService;
import pl.edu.agh.IUsersService;
import pl.edu.agh.exceptions.NotEnoughFundsException;
import pl.edu.agh.exceptions.SeatAlreadyOccupiedException;
import pl.edu.agh.exceptions.SeatNotFoundException;
import pl.edu.agh.model.Seat;
import pl.edu.agh.model.User;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import java.util.ArrayList;

@Singleton
@Lock
public class SeatsManager implements ISeatsManagerRemote {

    @EJB
    private ITicketsService ticketService;

    @EJB
    private ISeatsService seatsService;

    @EJB
    private IUsersService usersService;

//    @EJB
//    private ISeatsAvailabilityServiceRemote seatsAvailability;


    @Override
    public User userWithCredentials(String login, String password) {
        return usersService.userWithCredentials(login, password);
    }

    @Override
    public ArrayList<Seat> getSeatList() {
        return seatsService.getSeats();
    }

    @Override
    public int getSeatPrice(int seatNumber) throws SeatNotFoundException {
        return seatsService.getSeatById(seatNumber).getPrice();
    }

    @Override
    public void setUserForTransaction(User userForTransaction) {
        ticketService.setUser(userForTransaction);
    }

    @Override
    public Integer getUserBalance() {
        return ticketService.getUser().getBalance();
    }

    @Override
    public void buyTicket(int seatNumber) throws SeatNotFoundException, SeatAlreadyOccupiedException, NotEnoughFundsException {
        Seat seat = seatsService.getSeatById(seatNumber);
        if(seat.isOccupied())
            throw new SeatAlreadyOccupiedException();

        ticketService.charge(seat.getPrice());
        seat.setOccupied(true);
    }
}
