package pl.edu.agh.exceptions;

public class NotEnoughFundsException extends Exception {

    public NotEnoughFundsException() {
        super("You don't have enough funds");
    }
}
