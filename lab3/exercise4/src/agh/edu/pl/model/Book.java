package agh.edu.pl.model;

import java.util.ArrayList;

public class Book {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private String author;
    private Genre genre;
    private String title;
    private Integer pages;
    private Double price;
    private Currency currency;

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages() {
        return pages;
    }

    public Double getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Book(Integer id, String author, Genre genre, String title, Integer pages, Double price, Currency currency) {
        this.id = id;
        this.selected = false;
        this.author = author;
        this.genre = genre;
        this.title = title;
        this.pages = pages;
        this.price = price;
        this.currency = currency;
    }

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public enum Currency {
        ZL,
        USD,
        EUR
    }

    public enum Genre {
        THRILLER,
        FANTASY,
        ADVENTURE,
        AUTOBIOGRAPHY,
        SCIENCE_FICTION {
            @Override
            public String toString() {
                return "SCIENCE FICTION";
            }
        }
    }

    public static ArrayList<Book> books;
    static
    {
        books = new ArrayList<>();
        books.add(new Book(1, "John Flanagan", Genre.FANTASY, "The Ruins of Gorlan", 389, 20.99, Currency.USD));
        books.add(new Book(2, "J. R. R. Tolkien", Genre.FANTASY, " The Fellowship of the Ring", 340, 24.99, Currency.USD));
        books.add(new Book(3,"C. S. Lewis", Genre.FANTASY, " The Lion, the Witch and the Wardrobe", 325, 22.99, Currency.USD));
        books.add(new Book(4, "Chris Kyle", Genre.AUTOBIOGRAPHY, "American sniper", 489, 30.99, Currency.USD));
        books.add(new Book(5, "Craig Ferguson", Genre.AUTOBIOGRAPHY, "American on purpose", 330, 31.50, Currency.USD));
        books.add(new Book(6, "Kevin Brophy", Genre.THRILLER, "Another Kind of Country", 420, 15.00, Currency.EUR));
        books.add(new Book(7, "Graham Hurley", Genre.THRILLER, "Raid 42", 383, 22.50, Currency.USD));
        books.add(new Book(8,"Mary Shelley", Genre.SCIENCE_FICTION, "Frankenstein", 412, 42.50, Currency.USD));
        books.add(new Book(9, "Frank Herbert", Genre.SCIENCE_FICTION, "Dune", 319, 18.50, Currency.USD));
    }

}
