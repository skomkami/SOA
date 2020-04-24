package app.beans;

import app.model.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("readers")
@ApplicationScoped
public class Readers extends GenericBean<Reader> {

}
