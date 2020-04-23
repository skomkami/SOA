package app.dao;

import app.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("CategoriesDAO")
@ApplicationScoped
public class CategoriesDAO extends GenericEntityDAO<Category> {

    public CategoriesDAO() {
        super();
    }
}
