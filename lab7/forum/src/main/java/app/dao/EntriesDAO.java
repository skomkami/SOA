package app.dao;

import app.model.Entry;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("EntriesDAO")
@SessionScoped
public class EntriesDAO extends GenericEntityDAO<Entry> {
}
