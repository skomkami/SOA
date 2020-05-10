package app.beans;

import app.dao.EntityDAO;
import app.model.Author;
import app.model.Book;
import app.model.Reader;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Named("search")
@SessionScoped
public class Search extends EntityDAO {

    public Search() {
        super();
    }

    private Author authorForReaders;

    private java.util.Date startDateForReadersWhoReadAuthor;

    private java.util.Date endDateForReadersWhoReadAuthor;

    public Date getStartDateForReadersWhoReadAuthor() {
        return startDateForReadersWhoReadAuthor;
    }

    public void setStartDateForReadersWhoReadAuthor(Date startDateForReadersWhoReadAuthor) {
        this.startDateForReadersWhoReadAuthor = startDateForReadersWhoReadAuthor;
    }

    public Date getEndDateForReadersWhoReadAuthor() {
        return endDateForReadersWhoReadAuthor;
    }

    public void setEndDateForReadersWhoReadAuthor(Date endDateForReadersWhoReadAuthor) {
        this.endDateForReadersWhoReadAuthor = endDateForReadersWhoReadAuthor;
    }

    public Author getAuthorForReaders() {
        return authorForReaders;
    }

    public void setAuthorForReaders(Author authorForReaders) {
        this.authorForReaders = authorForReaders;
    }

    public List<Reader> getReadersWhoReadBookFromAuthorInPeriod() {
        String jpql = "SELECT l.reader FROM Loan l WHERE l.book.author = :author and (l.beginDate <= :endDate) and (:startDate <= l.endDate)";
        TypedQuery<Reader> query = em.createQuery(jpql, Reader.class);
        query.setParameter("author", authorForReaders);
        query.setParameter("startDate", startDateForReadersWhoReadAuthor);
        query.setParameter("endDate", endDateForReadersWhoReadAuthor);

        return query.getResultList();
    }

    public boolean isReadyToDisplayReadersWhoReadAuthorInPeriod() {
        return authorForReaders != null && startDateForReadersWhoReadAuthor != null && endDateForReadersWhoReadAuthor != null;
    }

    private Book bookForReadersWhoReadBook;

    public Book getBookForReadersWhoReadBook() {
        return bookForReadersWhoReadBook;
    }

    public void setBookForReadersWhoReadBook(Book bookForReadersWhoReadBook) {
        this.bookForReadersWhoReadBook = bookForReadersWhoReadBook;
    }

    private java.util.Date startDateForReadersWhoReadBook;

    public Date getStartDateForReadersWhoReadBook() {
        return startDateForReadersWhoReadBook;
    }

    public void setStartDateForReadersWhoReadBook(Date startDateForReadersWhoReadBook) {
        this.startDateForReadersWhoReadBook = startDateForReadersWhoReadBook;
    }

    public Date getEndDateForReadersWhoReadBook() {
        return endDateForReadersWhoReadBook;
    }

    public void setEndDateForReadersWhoReadBook(Date endDateForReadersWhoReadBook) {
        this.endDateForReadersWhoReadBook = endDateForReadersWhoReadBook;
    }

    private java.util.Date endDateForReadersWhoReadBook;

    public boolean isReadyToDisplayReadersWhoReadBookInPeriod() {
        return bookForReadersWhoReadBook != null && startDateForReadersWhoReadBook != null && endDateForReadersWhoReadBook != null;
    }

    public List<Reader> getReadersWhoReadBookInPeriod() {
        String jpql = "SELECT l.reader FROM Loan l WHERE l.book = :book and (l.beginDate <= :endDate) and (:startDate <= l.endDate)";
        TypedQuery<Reader> query = em.createQuery(jpql, Reader.class);
        query.setParameter("book", bookForReadersWhoReadBook);
        query.setParameter("startDate", startDateForReadersWhoReadBook);
        query.setParameter("endDate", endDateForReadersWhoReadBook);

        return query.getResultList();
    }

    private Reader readerForAuthorsReadBy;

    public Reader getReaderForAuthorsReadBy() {
        return readerForAuthorsReadBy;
    }

    public void setReaderForAuthorsReadBy(Reader readerForAuthorsReadBy) {
        this.readerForAuthorsReadBy = readerForAuthorsReadBy;
    }

    public List<Author> getAuthorsReadByReader() {
        String jpql = "SELECT l.book.author FROM Loan l WHERE l.reader = :reader";
        TypedQuery<Author> query = em.createQuery(jpql, Author.class);
        query.setParameter("reader", readerForAuthorsReadBy);
        return query.getResultList();
    }

    public Object[] getFavouriteAuthor() {
        String jpql = "SELECT a, count(a) as loaned_books FROM Loan l JOIN l.book b JOIN b.author a group by a order by loaned_books desc";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList().get(0);
    }
}
