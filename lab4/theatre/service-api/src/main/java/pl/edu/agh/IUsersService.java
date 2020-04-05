package pl.edu.agh;

import pl.edu.agh.model.User;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface IUsersService {
    ArrayList<User> getUsers();

    User userWithCredentials(String login, String password);
}
