package app.beans;

import app.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("books")
@ApplicationScoped
public class Books extends GenericBean<Book> {

}
