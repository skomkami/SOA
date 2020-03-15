package agh.edu.pl;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
@ManagedBean(name = "banner")
@ApplicationScoped
public class Banner implements Serializable {

    private int clicks = 0;

    public int getClicks() {
        return clicks;
    }

    public void incrementClicks() {
        ++this.clicks;
    }

    public Integer getRandomAd() {
        return (int)(Math.random() * 5 + 1);
    }
}
