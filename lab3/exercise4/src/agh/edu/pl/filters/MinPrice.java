package agh.edu.pl.filters;

import agh.edu.pl.model.Book;

import java.util.function.Predicate;

public class MinPrice implements Filter {

    private Predicate<Book> filterFunc;
    private Double price;

    public MinPrice(Double minPrice) {
        this.price = minPrice;
        this.filterFunc = book -> book.getPrice() >= minPrice;
    }

    @Override
    public Predicate<Book> getFilter() {
        return filterFunc;
    }

    @Override
    public String getInfo() {
        return "Min price " + price;
    }
}
