package app.beans;

import app.model.Book;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("books")
@SessionScoped
public class Books extends GenericBean<Book> {

}
