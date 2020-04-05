package pl.edu.agh.impl;

import pl.edu.agh.api.ISeatsAvailabilityServiceRemote;
import pl.edu.agh.api.ISeatsManagerRemote;
import pl.edu.agh.api.ISeatsServiceRemote;
import pl.edu.agh.api.ITicketsServiceRemote;
import pl.edu.agh.exceptions.NotEnoughFundsException;
import pl.edu.agh.exceptions.SeatAlreadyOccupiedException;
import pl.edu.agh.exceptions.SeatNotFoundException;
import pl.edu.agh.model.Seat;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import java.util.ArrayList;

@Singleton
@Lock
public class SeatsManager implements ISeatsManagerRemote {

    @EJB
    private ITicketsServiceRemote ticketService;

    @EJB
    private ISeatsServiceRemote seatsService;

    @EJB
    private ISeatsAvailabilityServiceRemote seatsAvailability;

    @Override
    public ArrayList<Seat> getSeatList() {
        return seatsService.getSeats();
    }

    @Override
    public int getSeatPrice(int seatNumber) throws SeatNotFoundException {
        return seatsService.getSeatById(seatNumber).getPrice();
    }

    @Override
    public void buyTicket(int seatNumber) throws SeatNotFoundException, SeatAlreadyOccupiedException, NotEnoughFundsException {
        Seat seat = seatsService.getSeatById(seatNumber);
        if(seat.isOccupied())
            throw new SeatAlreadyOccupiedException();
        seat.setOccupied(true);

        ticketService.charge(seat.getPrice());
    }
}
