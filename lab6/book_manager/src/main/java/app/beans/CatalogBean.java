package app.beans;

import app.dao.CatalogDAO;
import app.model.Catalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("catalog")
@ApplicationScoped
public class CatalogBean implements Serializable {

    @Inject
    private CatalogDAO catalogDAO;

    public void deleteCatalog() {
        try {
            for ( Catalog c: getSelectedCatalogs() )
                catalogDAO.remove(c);
        } catch (Exception e) {

        }
    }

    private Map<Integer, Boolean> checkedCatalogs = new HashMap<Integer, Boolean>();

    public Map<Integer, Boolean> getCheckedCatalogs() {
        return checkedCatalogs;
    }

    public List<Catalog> getCatalogsList() {
        return catalogDAO.getAll();
    }

    public List<Integer> getCatalogsIds() {
        return getCatalogsList().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Catalog> getSelectedCatalogs() {
        List<Catalog> selectedCatalogs = new ArrayList<>();
        for(Catalog catalog : getCatalogsList())
            if(checkedCatalogs.containsKey(catalog.getId()) && checkedCatalogs.get(catalog.getId()))
                selectedCatalogs.add(catalog);

        return selectedCatalogs;
    }
    private Catalog addCatalog = new Catalog();

    private Catalog editCatalog;

    public Catalog getEditCatalog() {
        return editCatalog;
    }
    public void setEditCatalog(Catalog editCatalog) {
        this.editCatalog = editCatalog;
    }

    public Integer getEditCatalogId() {
        return editCatalogId;
    }

    public void setEditCatalogId(Integer editCatalogId) {
        if ( editCatalogId != null ) {
            editCatalog = catalogDAO.find(editCatalogId);
        } else {
            editCatalog = null;
        }

        this.editCatalogId = editCatalogId;
    }

    private Integer editCatalogId;

    public Catalog getAddCatalog() {
        return addCatalog;
    }

    public void insertCatalog() {
        if ( this.addCatalog == null ) {
            System.err.println( "Catalog to insert is null");
        } else {
            catalogDAO.add(this.addCatalog);
            this.addCatalog = new Catalog();
        }
    }

    public void setAddCatalog(Catalog addCatalog) {
        this.addCatalog = addCatalog;
    }

    public boolean isInEditMode() {
        return editCatalogId != null;
    }

    public void editCatalogInDAO() {
        catalogDAO.edit(this.editCatalog);
        this.editCatalog = null;
        this.editCatalogId = null;
    }
}
