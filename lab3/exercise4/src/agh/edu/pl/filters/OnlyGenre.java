package agh.edu.pl.filters;

import agh.edu.pl.model.Book;

import java.util.function.Predicate;

public class OnlyGenre implements Filter {

    private Predicate<Book> filterFunc;
    private Book.Genre genre;

    public OnlyGenre(Book.Genre genre) {
        this.genre = genre;
        this.filterFunc = book -> book.getGenre() == genre;
    }

    @Override
    public Predicate<Book> getFilter() {
        return filterFunc;
    }

    @Override
    public String getInfo() {
        return "Only genre: " + genre;
    }
}
