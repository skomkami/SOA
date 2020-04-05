package pl.edu.agh.repos;

import pl.edu.agh.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UsersRepository {
    private ArrayList<User> users = new ArrayList<>();

    public UsersRepository() {
        users.addAll(
                Arrays.asList(
                        new User("jdelaney", "jdelaney", 100),
                        new User("tshelby", "tshelby", 100),
                        new User("wwhite", "wwhite", 80)
                )
        );
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
