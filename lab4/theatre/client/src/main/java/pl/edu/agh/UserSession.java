package pl.edu.agh;

import pl.edu.agh.api.ISeatsAvailabilityServiceRemote;
import pl.edu.agh.api.ISeatsManagerRemote;
import pl.edu.agh.api.ITicketsServiceRemote;
import pl.edu.agh.api.IUsersServiceRemote;
import pl.edu.agh.exceptions.SeatAlreadyOccupiedException;
import pl.edu.agh.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

@ManagedBean(name = "usersession")
@Stateful
@SessionScoped
public class UserSession implements Serializable {

    @EJB
    private IUsersServiceRemote usersService;

    @EJB
    private ITicketsServiceRemote ticketService;

    @EJB
    private ISeatsManagerRemote seatsManager;

    @EJB
    private ISeatsAvailabilityServiceRemote seatsAvailability;

    private String login;
    private String password;

    private User sessionUser;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logIn() throws ValidatorException {
        User user = usersService.userWithCredentials(login, password);

        if(user != null) {
            sessionUser = user;
            ticketService.setUser(sessionUser);
            return "seats_booking";
        } else {
            FacesMessage message = new FacesMessage("Wrong credentials");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("LOGIN", message);
            return "";
        }
    }

    public ArrayList<Integer> getSeats() {
        return seatsManager.getSeatList().stream().map( s -> s.getNumber()).collect(Collectors.toCollection(ArrayList::new));
    }

    private Integer seatNumber;

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void buy() {
        try {
            if(seatNumber == null)
                throw new Exception("Please select seat");

            if (!seatsAvailability.isSeatAvailable(seatNumber))
                throw new SeatAlreadyOccupiedException();

            seatsManager.buyTicket(seatNumber);

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("Purchase unsuccessful", message);
        }

    }

    public String checkLogin() {
        if(this.getSessionUser() == null)
            return "index.xhtml";
        else return "";
    }

    public String logOut() {
        this.sessionUser = null;
        ticketService.setUser(null);
        return "index.xhtml";
    }
}
