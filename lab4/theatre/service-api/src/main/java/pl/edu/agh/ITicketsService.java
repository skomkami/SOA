package pl.edu.agh;

import pl.edu.agh.exceptions.NotEnoughFundsException;
import pl.edu.agh.model.User;

import javax.ejb.Local;

@Local
public interface ITicketsService {

    void charge(int amount) throws NotEnoughFundsException;

    User getUser();
    void setUser(User u);
}
