package app.beans;

import app.model.Reader;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("readers")
@SessionScoped
public class Readers extends GenericBean<Reader> {

}
