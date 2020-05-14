package app.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends IdentifiableVersionedEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof Book)) return false;
        if(((Book) obj).getId() == this.getId() && ((Book) obj).getTitle().equals(this.getTitle()) && ((Book) obj).isbn.equals(this.getIsbn()))
            return true;
        else return false;

    }
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getId();
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());

        return result;
    }

}
