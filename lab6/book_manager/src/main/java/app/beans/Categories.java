package app.beans;

import app.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("categories")
@ApplicationScoped
public class Categories extends GenericBean<Category> {
}
