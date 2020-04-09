package pl.edu.agh.model;

import pl.edu.agh.exceptions.NotEnoughFundsException;

import java.io.Serializable;

public class User implements Serializable {

    private String login;
    private String password;
    private int balance;

    public User(String login, String password, int balance) {
        this.login = login;
        this.password = password;
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public boolean hasCredentials(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    public String getPassword() {
        return password;
    }

    public void credit(int amount) throws NotEnoughFundsException {
        if(this.balance < amount)
            throw new NotEnoughFundsException();
        this.balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
