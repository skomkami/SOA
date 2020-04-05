package pl.edu.agh.api;

import pl.edu.agh.model.User;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface IUsersServiceRemote {
    ArrayList<User> getUsers();

    User userWithCredentials(String login, String password);
}
