package pl.edu.agh;

import pl.edu.agh.exceptions.SeatNotFoundException;

import javax.ejb.Remote;

@Remote
public interface ISeatsAvailabilityServiceRemote {

    boolean isSeatAvailable(int seatNumber) throws SeatNotFoundException;

}
