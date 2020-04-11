package app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    private int id;
    private String authorSurname;
    private String authorName;
    private String title;
    private String isbn;
    private int year;
    private Double price;

    public Book() {
        super();
    }

    public Book(String authorSurname, String authorName, String title, String isbn, int year, Double price) {
        this.authorSurname = authorSurname;
        this.authorName = authorName;
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.price = price;
    }

    public Book(Book other) {
        this.authorSurname = other.authorSurname;
        this.authorName = other.authorName;
        this.title = other.title;
        this.isbn = other.isbn;
        this.year = other.year;
        this.price = other.price;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Column(name = "author_surname", nullable = false)
    public String getAuthorSurname() {
        return authorSurname;
    }

    @Column(name = "author_name", nullable = false)
    public String getAuthorName() {
        return authorName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name = "isbn", nullable = false)
    public String getIsbn() {
        return isbn;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "create_year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }
}
