package agh.edu.pl.filters;

import agh.edu.pl.model.Book;

import java.util.function.Predicate;

public class ExcludeGenre implements Filter {

    private Book.Genre genre;

    private Predicate<Book> filterFunc;

    public ExcludeGenre(Book.Genre genre) {
        this.genre = genre;
        this.filterFunc = book -> book.getGenre() != genre;
    }
    @Override
    public Predicate<Book> getFilter() {
        return filterFunc;
    }

    @Override
    public String getInfo() {
        return "Exclude genre: " + genre;
    }
}
