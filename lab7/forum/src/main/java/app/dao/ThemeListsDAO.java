package app.dao;

import app.model.ThemeList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("ThemeListsDAO")
@SessionScoped
public class ThemeListsDAO extends GenericEntityDAO<ThemeList> {

}
