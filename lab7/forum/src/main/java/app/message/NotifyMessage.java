package app.message;

import java.io.Serializable;
import java.util.ArrayList;

public class NotifyMessage implements Serializable {

    private String themeList;
    private String theme;

    private ArrayList<String> users;

    public NotifyMessage(String themeList, String theme, ArrayList<String> users) {
        this.themeList = themeList;
        this.theme = theme;
        this.users = users;
    }

    public String getThemeList() {
        return themeList;
    }

    public void setThemeList(String themeList) {
        this.themeList = themeList;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}
