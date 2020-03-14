package agh.edu.pl.filters;

import agh.edu.pl.model.Book;

import java.util.function.Predicate;

public class MaxPrice implements Filter {

    private Predicate<Book> filterFunc;

    private Double price;

    public MaxPrice(Double maxPrice) {
        this.price = maxPrice;
        this.filterFunc = book -> book.getPrice() <= maxPrice;
    }


    @Override
    public Predicate<Book> getFilter() {
        return filterFunc;
    }

    @Override
    public String getInfo() {
        return "Max price " + price;
    }
}
