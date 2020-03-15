package agh.edu.pl;

import agh.edu.pl.model.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean(name = "survey")
@SessionScoped
public class Survey implements Serializable {

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

    private Colors[] colors;
    private Frequency frequency;
    private Budget budget;
    private Clothes[] clothes;

    public void setClothes(Clothes[] clothes) {
        this.clothes = clothes;
    }

    public Clothes[] getClothes() {
        return clothes;
    }

    public void setColors(Colors[] colors) {
        this.colors = colors;
    }

    public Colors[] getColors() {
        return colors;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Frequency[] getFrequencies() {
        return Frequency.values();
    }

    public Colors[] getColorsValues() {
        return Colors.values();
    }

    public String addSurvey() {
        return "summary";
    }

    public Clothes[] getFemaleClothes() {
        return new Clothes[]{Clothes.SUITS, Clothes.BLOUSES, Clothes.SKIRTS, Clothes.TROUSERS};
    }

    public Clothes[] getMaleClothes() {
        return new Clothes[]{Clothes.TROUSERS, Clothes.SHORTS, Clothes.SUITS, Clothes.SHIRTS, Clothes.NECKTIES};
    }

}
