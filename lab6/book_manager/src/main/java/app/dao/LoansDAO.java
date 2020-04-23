package app.dao;

import app.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("LoansDAO")
@ApplicationScoped
public class LoansDAO extends GenericEntityDAO<Loan> {
    public LoansDAO() {
        super();
    }
}
