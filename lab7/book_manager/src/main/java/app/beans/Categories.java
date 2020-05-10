package app.beans;

import app.model.Category;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("categories")
@SessionScoped
public class Categories extends GenericBean<Category> {
}
