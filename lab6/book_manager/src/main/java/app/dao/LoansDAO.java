package app.dao;

import app.model.Loan;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named("LoansDAO")
@SessionScoped
public class LoansDAO extends GenericEntityDAO<Loan> {
    public LoansDAO() {
        super();
    }
}
