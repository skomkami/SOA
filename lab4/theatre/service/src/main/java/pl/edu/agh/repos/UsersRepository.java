package pl.edu.agh.repos;

import pl.edu.agh.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UsersRepository {
    private ArrayList<User> users = new ArrayList<>();

    public UsersRepository() {
        users.addAll(
                Arrays.asList(
                        new User("James Delaney", "jdelaney", 100),
                        new User("Thomas Shelby", "tshelby", 100),
                        new User("Walter White", "wwhite", 80)
                )
        );
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
