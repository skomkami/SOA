package app.beans;

import app.model.Catalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("catalog")
@ApplicationScoped
public class CatalogBean extends GenericBean<Catalog> {

}
