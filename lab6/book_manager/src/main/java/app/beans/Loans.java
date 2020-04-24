package app.beans;

import app.dao.CatalogDAO;
import app.model.Catalog;
import app.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("loans")
@ApplicationScoped
public class Loans extends GenericBean<Loan> {

    @Inject
    private CatalogDAO catalogDAO;

    public void insertEntity() {
        if ( this.addEntity == null ) {
            System.err.println( "Loan to insert is null");
        } else {

            Catalog catalog = catalogDAO.getCatalogWithBookId(addEntity.getBook().getId());

            if (catalog.getInStock() <= 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("addLoan", new FacesMessage("There are no books left in stock. Please wait until somebody returns one."));
                return;
            }

            catalog.setInStock(catalog.getInStock() - 1 );
            catalogDAO.edit(catalog);
            dao.add(this.addEntity);
            this.addEntity = new Loan();

        }
    }

    public void delete() {
        try {
            for ( Loan l: getSelectedList() ) {
                int bookId = l.getBook().getId();
                Catalog catalog = catalogDAO.getCatalogWithBookId(bookId);
                catalog.setInStock(catalog.getInStock() + 1);
                catalogDAO.edit(catalog);
                dao.remove(l);
            }
        } catch (Exception e) {

        }
    }

}
