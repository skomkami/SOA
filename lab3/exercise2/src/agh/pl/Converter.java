package agh.pl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.DoubleToLongFunction;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class Converter implements Serializable{

    public String fromCurrency;
    public String toCurrency;

    public String getFromCurrency() {
        return fromCurrency;
    }
    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public void add() {
        currenciesValues.put(country, value);
    }

    private String country;
    private String value;

    public String getCountry() {
        return country;
    }

    public String getValue() {
        return value;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private static Map<String, Double> currenciesValues;
    static
    {
        currenciesValues = new LinkedHashMap<String, Double>();

        currenciesValues.put("PL", 1.0);
        currenciesValues.put("EUR", 4.3);
    }

    public Map<String, Double> getCurrenciesValues()
    {
        return currenciesValues;
    }

}