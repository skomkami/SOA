package agh.edu.pl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

@SessionScoped
@ManagedBean( name = "randombean")
public class RandomBean {
    static int rand = (int)(Math.random() * 5 + 1);

    public void checkPage(Integer page) throws IOException {
        if (page == rand) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/trafiony.xhtml");
        }
    }

    public void nextRand() {
        rand = (int)(Math.random() * 5 + 1);
    }

    public int getRand() {
        return rand;
    }

}
