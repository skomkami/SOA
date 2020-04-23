package app.beans;

import app.dao.*;
import app.model.Author;
import app.model.Book;
import app.model.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named("search")
@ApplicationScoped
public class Search extends EntityDAO {

    public Search() {
        super();
    }

    @Inject
    private BooksDAO booksDAO;

//    @Inject
//    private ReadersDAO readersDAO;
//
    @Inject
    private AuthorsDAO authorsDAO;
//
//    @Inject
//    private CategoriesDAO categoriesDAO;
//
//    @Inject
//    private LoansDAO loansDAO;
//
//    @Inject
//    private CatalogDAO catalogDAO;

    public List<Book> getFoundBooks() {
        return booksDAO.getBooks();
    }

    public List<Author> getAuthorsList() {
        return authorsDAO.getAuthors();
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
}
