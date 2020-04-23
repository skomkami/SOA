package app.beans;

import app.dao.AuthorsDAO;
import app.model.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("authors")
@ApplicationScoped
public class Authors implements Serializable {

    @Inject
    private AuthorsDAO authorsDAO;

    public void deleteAuthor() {
        try {
            for ( Author b: getSelectedAuthors() )
                authorsDAO.removeAuthor(b);
        } catch (Exception e) {

        }
    }

    public Author getAuthor(int id) {
        return authorsDAO.findAuthor(id);
    }

    private Map<Integer, Boolean> checkedAuthors = new HashMap<Integer, Boolean>();

    public Map<Integer, Boolean> getCheckedAuthors() {
        return checkedAuthors;
    }

    public List<Author> getAuthorsList() {
        return authorsDAO.getAuthors();
    }

    public List<Integer> getAuthorsIds() {
        return getAuthorsList().stream().map(b -> b.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Author> getSelectedAuthors() {
        List<Author> selectedAuthors = new ArrayList<>();
        for(Author author : getAuthorsList())
            if(checkedAuthors.containsKey(author.getId()) && checkedAuthors.get(author.getId()))
                selectedAuthors.add(author);

        return selectedAuthors;
    }
    private Author addAuthor = new Author();

    private Author editAuthor;

    public Author getEditAuthor() {
        return editAuthor;
    }
    public void setEditAuthor(Author editAuthor) {
        this.editAuthor = editAuthor;
    }

    public Integer getEditAuthorId() {
        return editAuthorId;
    }

    public void setEditAuthorId(Integer editAuthorId) {
        if ( editAuthorId != null ) {
            editAuthor = authorsDAO.findAuthor(editAuthorId);
        } else {
            editAuthor = null;
        }

        this.editAuthorId = editAuthorId;
    }

    private Integer editAuthorId;

    public Author getAddAuthor() {
        return addAuthor;
    }

    public void insertAuthor() {
        if ( this.addAuthor == null ) {
            System.err.println( "Author to insert is null");
        } else {


            authorsDAO.addAuthor(this.addAuthor);
            this.addAuthor = new Author();
        }
    }

    public void setAddAuthor(Author addAuthor) {
        this.addAuthor = addAuthor;
    }

    public boolean isInEditMode() {
        return editAuthorId != null;
    }

    public void editAuthorInDAO() {
        authorsDAO.editAuthor(this.editAuthor);
        this.editAuthor = null;
        this.editAuthorId = null;
    }
}
