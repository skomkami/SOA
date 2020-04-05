package pl.edu.agh.impl;

import pl.edu.agh.api.ITicketsServiceRemote;
import pl.edu.agh.exceptions.NotEnoughFundsException;
import pl.edu.agh.model.User;

import javax.ejb.Stateful;

@Stateful
public class TicketsService implements ITicketsServiceRemote {

    private User user;

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void charge(int amount) throws NotEnoughFundsException {
        user.credit(amount);
    }
}
