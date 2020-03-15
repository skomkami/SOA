package agh.edu.pl;

import agh.edu.pl.model.Person;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "survey")
@ViewScoped
public class Survey implements Serializable {
    @ManagedProperty(value="#{banner}")
    private Banner banner;

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public void incrementClicks() {
        banner.incrementClicks();
    }

    public int getClicks() {
        return banner.getClicks();
    }

    private Person person;

    public Survey() {
        this.person = new Person();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person.Gender[] getGenders() {
        return Person.Gender.values();
    }
}
