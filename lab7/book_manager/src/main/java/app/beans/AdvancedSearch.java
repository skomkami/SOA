package app.beans;

import app.dao.EntityDAO;
import app.model.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("advancedSearch")
@SessionScoped
public class AdvancedSearch extends EntityDAO {

    private String authorName;
    private String authorSurname;
    private String bookTitle;
    private Category bookCategory;
    private String readerName;
    private String readerSurname;
    private java.util.Date startLoanDate;
    private java.util.Date endLoanDate;

    private List<Object[]> result;

    public List<Object[]> getResult() {
        return result;
    }

    public void setResult(List<Object[]> result) {
        this.result = result;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Category getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(Category bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderSurname() {
        return readerSurname;
    }

    public void setReaderSurname(String readerSurname) {
        this.readerSurname = readerSurname;
    }

    public Date getStartLoanDate() {
        return startLoanDate;
    }

    public void setStartLoanDate(Date startLoanDate) {
        this.startLoanDate = startLoanDate;
    }

    public Date getEndLoanDate() {
        return endLoanDate;
    }

    public void setEndLoanDate(Date endLoanDate) {
        this.endLoanDate = endLoanDate;
    }

    public void search () {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);

        Root<Loan> loan = query.from(Loan.class);
        Join<Loan, Reader> loanAndReader = loan.join("reader");
        Join<Loan, Book> loanAndBook = loan.join("book");
        Join<Book, Author> bookAndAuthor = loanAndBook.join("author");
//        Join<Book, Category> all = readerLoanBookAuthor.join("category");

        List<Predicate> conditions = new ArrayList<>();

        if (authorName != null && !authorName.equals("")) {
            conditions.add(cb.equal(bookAndAuthor.get("firstName"), authorName));
        }

        if (authorSurname != null && !authorSurname.equals("")) {
            conditions.add(cb.equal(bookAndAuthor.get("lastName"), authorSurname));
        }

        if (readerName != null && !readerName.equals("")) {
            conditions.add(cb.equal(loanAndReader.get("firstName"), readerName));
        }

        if (authorSurname != null && !authorSurname.equals("")) {
            conditions.add(cb.equal(loanAndReader.get("lastName"), authorSurname));
        }

        if (bookTitle != null && !bookTitle.equals("")) {
            conditions.add(cb.equal(loanAndBook.get("title"), bookTitle));
        }

        if (bookCategory != null) {
            conditions.add(cb.equal(loanAndBook.get("category"), bookCategory));
        }

        if (startLoanDate != null) {
            conditions.add(cb.greaterThan(loan.get("beginDate"), startLoanDate));
        }

        if (endLoanDate != null) {
            conditions.add(cb.lessThan(loan.get("endDate"), endLoanDate));
        }

        CriteriaQuery<Object[]> multiTableQuery = query.multiselect(loan, loanAndReader, loanAndBook, bookAndAuthor).where(conditions.toArray(new Predicate[]{}));
        result = em.createQuery(multiTableQuery).getResultList();

    }

}
