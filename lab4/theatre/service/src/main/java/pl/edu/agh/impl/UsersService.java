package pl.edu.agh.impl;

import pl.edu.agh.api.IUsersServiceRemote;
import pl.edu.agh.model.User;
import pl.edu.agh.repos.UsersRepository;

import javax.ejb.Singleton;
import java.util.ArrayList;

@Singleton
public class UsersService implements IUsersServiceRemote {

    private UsersRepository usersRepository;

    public UsersService() {
        usersRepository = new UsersRepository();
    }

    @Override
    public ArrayList<User> getUsers() {
        return usersRepository.getUsers();
    }

    @Override
    public User userWithCredentials(String login, String password) {
        return this.getUsers()
                .stream()
                .filter(u -> u.hasCredentials(login, password))
                .findFirst()
                .orElse(null);
    }
}
