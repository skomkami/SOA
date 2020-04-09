package agh.edu.pl.converter;

import agh.edu.pl.model.Book;

import java.util.LinkedHashMap;
import java.util.Map;

public class Converter {

    private static Map<Book.Currency, Double> currenciesValues;
    static
    {
        currenciesValues = new LinkedHashMap<>();

        currenciesValues.put(Book.Currency.PLN, 1.0);
        currenciesValues.put(Book.Currency.EUR, 4.3);
        currenciesValues.put(Book.Currency.USD, 3.9);
    }

    public Map<Book.Currency, Double> getCurrenciesValues()
    {
        return currenciesValues;
    }

    public static Double convertToPLN(Double amount, Book.Currency currency) {
        Double result = amount * currenciesValues.get(currency);
        return result;
    }

}