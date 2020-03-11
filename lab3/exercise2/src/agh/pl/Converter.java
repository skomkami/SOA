package agh.pl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
@ManagedBean
@SessionScoped
public class Converter implements Serializable{

    private Double fromCurrency;
    private Double toCurrency = getCurrenciesValues().get("EUR");
    private Double amount = 1.0;

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getFromCurrency() {
        return fromCurrency;
    }
    public void setFromCurrency(Double fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Double getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Double toCurrency) {
        this.toCurrency = toCurrency;
    }

    public void add() {
        currenciesValues.put(currency, value);
    }

    private String currency;
    private Double value;

    public String getCurrency() {
        return currency;
    }

    public Double getValue() {
        return value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setValue(Double value) {
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

    public String result() {
        Double result = amount*fromCurrency/toCurrency;
        System.out.println("Amount in new currency equals " + result);
        return "result";
    }

}