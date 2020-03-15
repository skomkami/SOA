package agh.edu.pl.model;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.bind.ValidationException;

public class Person {

    private String name;
    private String email;
    private Integer age;
    private Gender gender;
    private String education;
    private Integer height;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
        setHeight(this.height);
    }

    public void setEducation(String education) {
        this.education = education;
    }

    private Boolean isHeightInMaleRange(Integer height) {
        return height >= 165 && height <= 200;
    }

    private Boolean isHeightInFemaleRange(Integer height) {
        return height >= 150 && height <= 185;
    }

    public void setHeight(Integer height) {
        if(this.gender != null)
            if(this.gender == Gender.FEMALE && !isHeightInFemaleRange(height)
            || this.gender == Gender.MALE && !isHeightInMaleRange(height)) {

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("mainSurvey:height", new FacesMessage("Height not accurate for " + this.gender.name().toLowerCase() + "s"));

            }

        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEducation() {
        return education;
    }

    public Integer getHeight() {
        return height;
    }

    public enum Gender {
        MALE,
        FEMALE
    }
}
