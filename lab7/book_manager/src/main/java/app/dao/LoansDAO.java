package app.dao;

import app.model.Book;
import app.model.Loan;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;

@Named("LoansDAO")
@SessionScoped
public class LoansDAO extends GenericEntityDAO<Loan> {
    public LoansDAO() {
        super();
    }

    public boolean isBookLoaned(Book book) {
        String jpql = "SELECT l FROM Loan l WHERE l.book = :b";
        TypedQuery<Loan> query = em.createQuery(jpql, Loan.class);
        query.setParameter("b", book);

        return !query.getResultList().isEmpty();
    }
}
