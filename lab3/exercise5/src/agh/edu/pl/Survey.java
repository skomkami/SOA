package agh.edu.pl;

import agh.edu.pl.model.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

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

//    public boolean isBasicPartValid() {
//        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
//        Set<Map.Entry<String, Object>> componentStream = view.getViewMap().entrySet();
//        boolean baseCommon = componentStream
//                .stream()
//                .filter( e -> e.getKey().startsWith("baseCommon"))
//                .map(e -> ((UIInput)e.getValue()).isValid())
//                .reduce(true, (a,b) -> a && b );
//
//        if ( baseCommon ) {
//            if ( this.person.getIsFemale() ) {
//                return componentStream
//                        .stream()
//                        .filter( e -> e.getKey().startsWith("baseFemale"))
//                        .map(e -> ((UIInput)e.getValue()).isValid())
//                        .reduce(true, (a,b) -> a && b );
//            } else {
//                return componentStream
//                        .stream()
//                        .filter( e -> e.getKey().startsWith("baseMale"))
//                        .map(e -> ((UIInput)e.getValue()).isValid())
//                        .reduce(true, (a,b) -> a && b );
//            }
//        } else {
//            return false;
//        }
//    }

    public void sendBasePart() {
        basePartFilled = true;
    }

    private boolean basePartFilled = false;

    public boolean isBasicPartFilled() {
        return basePartFilled;
    }

    public Education[] getEducations() {
        return Education.values();
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
