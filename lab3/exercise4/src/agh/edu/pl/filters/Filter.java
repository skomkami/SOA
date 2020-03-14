package agh.edu.pl.filters;

import agh.edu.pl.model.Book;

import java.util.function.Predicate;

public interface Filter {

    Predicate<Book> getFilter();

    String getInfo();

    public enum FilterType {
        MAX_PRICE,
        MIN_PRICE,
        ONLY_GENRE,
        EXCLUDE_GENRE,
    }
}

