package app.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Table( name = "catalog")
@Entity
public class Catalog extends IdentifiableVersionedEntity {

    @OneToOne
    @JoinColumn(name = "book_id", unique = true)
    private Book book;

    @Column(name = "in_stock", nullable = false)
    @Min(0)
    private int inStock;

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
