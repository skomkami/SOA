package app.beans;

import app.dao.*;
import app.model.*;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("loans")
@ApplicationScoped
public class Loans implements Serializable {

    @Inject
    private LoansDAO loansDAO;

    @Inject
    private BooksDAO booksDAO;

    @Inject
    private ReadersDAO readersDAO;

    @Inject
    private CatalogDAO catalogDAO;

    public List<Integer> getBooksIds() {
        return booksDAO.getAll().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Integer> getReadersIds() {
        return readersDAO.getAll().stream().map(r -> r.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    private Integer addLoanBookId;
    private Integer addLoanReaderId;

    public Integer getAddLoanBookId() {
        return addLoanBookId;
    }

    public void setAddLoanBookId(Integer addLoanBookId) {
        this.addLoanBookId = addLoanBookId;
    }

    public Integer getAddLoanReaderId() {
        return addLoanReaderId;
    }

    public void setAddLoanReaderId(Integer addLoanReaderId) {
        this.addLoanReaderId = addLoanReaderId;
    }

    private Integer editLoanBookId;
    private Integer editLoanReaderId;

    public Integer getEditLoanBookId() {
        return editLoanBookId;
    }

    public void setEditLoanBookId(Integer editLoanBookId) {
        this.editLoanBookId = editLoanBookId;
    }

    public Integer getEditLoanReaderId() {
        return editLoanReaderId;
    }

    public void setEditLoanReaderId(Integer editLoanReaderId) {
        this.editLoanReaderId = editLoanReaderId;
    }

    public void deleteLoan() {
        try {
            for ( Loan l: getSelectedLoans() ) {
                int bookId = l.getBook().getId();
                Catalog catalog = catalogDAO.getCatalogWithBookId(bookId);
                catalog.setInStock(catalog.getInStock() + 1);
                catalogDAO.edit(catalog);
                loansDAO.remove(l);
            }
        } catch (Exception e) {

        }
    }

    private Map<Integer, Boolean> checkedLoans = new HashMap<Integer, Boolean>();

    public Map<Integer, Boolean> getCheckedLoans() {
        return checkedLoans;
    }

    public List<Loan> getLoansList() {
        return loansDAO.getAll();
    }

    public List<Integer> getLoansIds() {
        return getLoansList().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Loan> getSelectedLoans() {
        List<Loan> selectedLoans = new ArrayList<>();
        for(Loan loan : getLoansList())
            if(checkedLoans.containsKey(loan.getId()) && checkedLoans.get(loan.getId()))
                selectedLoans.add(loan);

        return selectedLoans;
    }
    private Loan addLoan = new Loan();

    private Loan editLoan;

    public Loan getEditLoan() {
        return editLoan;
    }
    public void setEditLoan(Loan editLoan) {
        this.editLoan = editLoan;
    }

    public Integer getEditLoanId() {
        return editLoanId;
    }

    public void setEditLoanId(Integer editLoanId) {
        if ( editLoanId != null ) {
            editLoan = loansDAO.find(editLoanId);
            editLoanBookId = editLoan.getBook().getId();
            editLoanReaderId = editLoan.getReader().getId();
        } else {
            editLoan = null;
        }

        this.editLoanId = editLoanId;
    }

    private Integer editLoanId;

    public Loan getAddLoan() {
        return addLoan;
    }

    public void insertLoan() {
        if ( this.addLoan == null ) {
            System.err.println( "Loan to insert is null");
        } else {
            if (addLoanBookId == null || addLoanReaderId == null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("addLoan", new FacesMessage("Set bookId and readerId"));
                return;
            }

            Book book = booksDAO.find(addLoanBookId);
            Catalog catalog = catalogDAO.getCatalogWithBookId(book.getId());

            if (catalog.getInStock() <= 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("addLoan", new FacesMessage("There are no books left in stock. Please wait until somebody returns one."));
                return;
            }

            catalog.setInStock(catalog.getInStock() - 1 );
            catalogDAO.edit(catalog);
            Reader reader = readersDAO.find(addLoanReaderId);
            addLoan.setBook(book);
            addLoan.setReader(reader);
            loansDAO.add(this.addLoan);
            this.addLoan = new Loan();

        }
    }

    public void setAddLoan(Loan addLoan) {
        this.addLoan = addLoan;
    }

    public boolean isInEditMode() {
        return editLoanId != null;
    }

    public void editLoanInDAO() {

        if (this.editLoanBookId != editLoan.getBook().getId()) {
            Book book = booksDAO.find(editLoanBookId);
            editLoan.setBook(book);
        }
        if (this.editLoanReaderId != editLoan.getReader().getId()) {
            Reader reader = readersDAO.find(editLoanReaderId);
            editLoan.setReader(reader);
        }

        loansDAO.edit(this.editLoan);
        this.editLoan = null;
        this.editLoanId = null;
    }
}
