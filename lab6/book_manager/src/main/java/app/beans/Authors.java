package app.beans;

import app.model.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("authors")
@ApplicationScoped
public class Authors extends GenericBean<Author> {

}
