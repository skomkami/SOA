package app.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Table( name = "catalog")
@Entity
public class Catalog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "book_id", unique = true)
    private Book book;

    @Column(name = "in_stock", nullable = false)
    @Min(0)
    private int inStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}
