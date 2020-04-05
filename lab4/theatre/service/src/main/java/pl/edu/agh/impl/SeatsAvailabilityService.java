package pl.edu.agh.impl;

import pl.edu.agh.api.ISeatsAvailabilityServiceRemote;
import pl.edu.agh.api.ISeatsService;
import pl.edu.agh.exceptions.SeatNotFoundException;
import pl.edu.agh.model.Seat;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SeatsAvailabilityService implements ISeatsAvailabilityServiceRemote {

    @EJB
    private ISeatsService seatsService;

    @Override
    public boolean isSeatAvailable(int seatNumber) throws SeatNotFoundException {
        Seat s = seatsService.getSeatById(seatNumber);
        return !s.isOccupied();
    }
}
