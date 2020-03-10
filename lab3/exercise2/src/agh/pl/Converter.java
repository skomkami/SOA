package agh.pl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Converter implements Serializable {
    public String getDefaultFrom() {
        return "PL";
    }

    private static Map<String,Double> currencyValue;
    static{
        currencyValue = new LinkedHashMap<String,Double>();
        currencyValue.put("PL", 1.0);
        currencyValue.put("EUR", 4.3);
    }

    public Map<String,Double> getCurrencyValue() {
        return currencyValue;
    }
}
