package pl.edu.agh.impl;

import pl.edu.agh.api.ISeatsServiceRemote;
import pl.edu.agh.exceptions.SeatNotFoundException;
import pl.edu.agh.model.Seat;
import pl.edu.agh.repos.SeatsRepository;

import javax.ejb.Singleton;
import java.util.ArrayList;

@Singleton
public class SeatsService implements ISeatsServiceRemote {

    private SeatsRepository seatsRepository;

    public SeatsService() {
        seatsRepository = new SeatsRepository();
    }

    @Override
    public ArrayList<Seat> getSeats() {
        return seatsRepository.getSeats();
    }

    @Override
    public Seat getSeatById(int number) throws SeatNotFoundException {
        return getSeats()
                .stream()
                .filter(s -> s.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new SeatNotFoundException(number));
    }
}
