package agh.edu.pl;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;

@ApplicationScoped
@ManagedBean(name = "counterbean")
public class CounterBean {

    public static HashMap<Integer, Integer> counter = new HashMap<>();

    public CounterBean() {
        counter.put(1,0);
        counter.put(2,0);
        counter.put(3,0);
        counter.put(4,0);
        counter.put(5,0);
    }

    public Integer getNumberOfVisits(Integer pageIndex) {
        return counter.get(pageIndex);
    }

    public void incrementVisitsCounter(Integer pageIndex) {
        counter.replace(pageIndex, counter.get(pageIndex) + 1 );
    }

}
