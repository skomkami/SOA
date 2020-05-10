package app.beans;

import app.model.Author;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("authors")
@SessionScoped
public class Authors extends GenericBean<Author> {

}
