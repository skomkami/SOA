package app.dao;

import app.model.Category;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named("CategoriesDAO")
@SessionScoped
public class CategoriesDAO extends GenericEntityDAO<Category> {

    public CategoriesDAO() {
        super();
    }
}
