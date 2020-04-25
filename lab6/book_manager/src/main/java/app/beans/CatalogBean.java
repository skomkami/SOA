package app.beans;

import app.model.Catalog;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("catalog")
@SessionScoped
public class CatalogBean extends GenericBean<Catalog> {

}
