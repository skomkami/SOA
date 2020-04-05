package pl.edu.agh.api;

import pl.edu.agh.exceptions.SeatAlreadyOccupiedException;
import pl.edu.agh.exceptions.SeatNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Remote;

@Remote
public interface ISeatsAvailabilityServiceRemote {

    boolean isSeatAvailable(int seatNumber) throws SeatNotFoundException, SeatAlreadyOccupiedException;

}
