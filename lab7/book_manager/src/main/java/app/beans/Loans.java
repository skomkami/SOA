package app.beans;

import app.dao.LoansDAO;
import app.model.Book;
import app.model.Loan;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("loans")
@SessionScoped
public class Loans extends GenericBean<Loan> {

    @Inject
    LibraryBean libraryBean;

    @Inject
    LoansDAO loansDAO;

    public void insertEntity() {
        if ( this.addEntity == null ) {
            System.err.println( "Loan to insert is null");
        } else {
            Book b = addEntity.getBook();
            if (loansDAO.isBookLoaned(b)) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("Loan", new FacesMessage("Book is already loaned. We're sorry."));
                return;
            }

            addEntity.setReader(libraryBean.getReader());

            dao.add(this.addEntity);
            this.addEntity = new Loan();
        }
    }

    public void delete() {
        try {
            for ( Loan l: getSelectedList() ) {
                int bookId = l.getBook().getId();
                dao.remove(l);
            }
        } catch (Exception e) {

        }
    }

}
