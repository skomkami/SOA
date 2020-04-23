package app.dao;

import app.model.Loan;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Named("LoansDAO")
@ApplicationScoped
public class LoansDAO extends EntityDAO {
    public LoansDAO() {
        super();
    }

    public List<Loan> getLoans() {
        List<Loan> loans = new ArrayList<>();
        try {
            Query q = em.createQuery("FROM Loan ", Loan.class);
            loans = q.getResultList();
        }
        catch(Exception e) {
            System.err.println("Error while fetching data: " + e);
        }
        return loans;
    }

    public void addLoan(Loan loan) {
        try {
            em.getTransaction().begin();
            em.persist(loan);
            em.getTransaction().commit();
            System.out.println("Loan with id: " + loan.getId() + " written into database");
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error during record insert: " + e);
        }
    }

    public void removeLoan(Loan loan) {
        try {
            em.getTransaction().begin();
            em.remove(loan);
            em.getTransaction().commit();
            System.out.println("Loan with id: " + loan.getId() + " removed from database");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while removing loan with id: " + loan.getId());
        }

    }

    public void editLoan(Loan loan) {
        try {
            em.getTransaction().begin();
            em.merge(loan);
            em.getTransaction().commit();
            System.out.println("Successfully updated loan with id: " + loan.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error while updating loan with id: " + loan.getId());
        }
    }

    public Loan findLoan(int id) {
        return em.find(Loan.class, id);
    }
}
