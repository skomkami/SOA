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
    private CatalogDAO catalogDAO;

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

            Catalog catalog = catalogDAO.getCatalogWithBookId(addLoan.getBook().getId());

            if (catalog.getInStock() <= 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("addLoan", new FacesMessage("There are no books left in stock. Please wait until somebody returns one."));
                return;
            }

            catalog.setInStock(catalog.getInStock() - 1 );
            catalogDAO.edit(catalog);
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
        loansDAO.edit(this.editLoan);
        this.editLoan = null;
        this.editLoanId = null;
    }
}
