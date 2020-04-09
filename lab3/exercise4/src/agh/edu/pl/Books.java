package agh.edu.pl;

import agh.edu.pl.converter.Converter;
import agh.edu.pl.filters.*;
import agh.edu.pl.model.Book;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean(name = "books")
@SessionScoped
public class Books implements Serializable {

    private List<Book> books;

    private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();

    private boolean originalCurrency = true;

    public Books() {
        books = Book.getBooks();
    }

    public void setOriginalCurrency(boolean originalCurrency) {
        if ( !originalCurrency ) {
            for (Book b: books ) {
                b.setPrice( Converter.convertToPLN(b.getPrice(), b.getCurrency()));
                b.setCurrency( Book.Currency.PLN);
            }
        }
        else {
            this.books = Book.getBooks();
            applyFilters();
        }
        this.originalCurrency = originalCurrency;
    }

    public boolean isOriginalCurrency() {
        return originalCurrency;
    }

    public String submit() {
        return "result";
    }

    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    public List<Book> getBooksList() {
        return this.books;
    }

    public ArrayList<Book> getSelectedBooks() {
        ArrayList<Book> selectedBooks = new ArrayList<>();
        for(Book book : books)
            if(checked.containsKey(book.getId()) && checked.get(book.getId()))
                selectedBooks.add(book);

        return selectedBooks;
    }

    public int getSelectedCount() {
        return getSelectedBooks().size();
    }

    public Double getCompoundPrice() {

        ArrayList<Book> selected = getSelectedBooks();
        for (Book b: selected ) {
            b.setPrice( Converter.convertToPLN(b.getPrice(), b.getCurrency()));
            b.setCurrency( Book.Currency.PLN);
        }
        return selected.stream().map(b -> b.getPrice()).reduce(0.0, (p1, p2) -> p1 + p2);
    }

    public Book.Genre[] getGenres() {
        return Book.Genre.values();
    }

    public Filter.FilterType[] getPriceFilters() {
        return new Filter.FilterType[]{Filter.FilterType.MAX_PRICE, Filter.FilterType.MIN_PRICE};
    }

    public Filter.FilterType[] getGenreFilters() {
        return new Filter.FilterType[]{Filter.FilterType.ONLY_GENRE, Filter.FilterType.EXCLUDE_GENRE};
    }

    private ArrayList<Filter> filters = new ArrayList<>();

    public ArrayList<Filter> getFilters() {
        return filters;
    }

    private Filter.FilterType genreFilterType;
    private Book.Genre filterGenre;

    public void setFilterGenre(Book.Genre filterGenre) {
        this.filterGenre = filterGenre;
    }

    public Filter.FilterType getGenreFilterType() {
        return genreFilterType;
    }

    public Book.Genre getFilterGenre() {
        return filterGenre;
    }


    public void setGenreFilterType(Filter.FilterType genreFilterType) {
        this.genreFilterType = genreFilterType;
    }

    public void addGenreFilter() throws Exception {
        switch (genreFilterType) {
            case ONLY_GENRE: filters.add(new OnlyGenre(filterGenre)); break;
            case EXCLUDE_GENRE: filters.add(new ExcludeGenre(filterGenre)); break;
            default: throw new Exception("Unexpected genre");
        }
        applyFilters();
    }

    private Filter.FilterType priceFilterType;
    private Double filterPrice;

    public void setPriceFilterType(Filter.FilterType priceFilterType) {
        this.priceFilterType = priceFilterType;
    }

    public Filter.FilterType getPriceFilterType() {
        return priceFilterType;
    }

    public Double getFilterPrice() {
        return filterPrice;
    }

    public void setFilterPrice(Double filterPrice) {
        this.filterPrice = filterPrice;
    }


    public void addPriceFilter() throws Exception {
        switch (priceFilterType) {
            case MIN_PRICE: filters.add(new MinPrice(filterPrice)); break;
            case MAX_PRICE: filters.add(new MaxPrice(filterPrice));break;
            default: throw new Exception("Unexpected price");
        }
        applyFilters();
    }

    public void applyFilters() {
        Stream<Book> bs = this.books.stream();

        for(Filter f: this.filters)
            bs = bs.filter(f.getFilter());

        this.books = bs.collect(Collectors.toCollection(ArrayList::new));

    }
}
