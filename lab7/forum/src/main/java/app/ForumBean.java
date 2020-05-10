package app;

import app.dao.NotificationsDAO;
import app.dao.ThemeListsDAO;
import app.dao.UsersDAO;
import app.model.Notification;
import app.model.ThemeList;
import app.model.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;


@Named("forum")
@SessionScoped
public class ForumBean implements Serializable {

    @Inject
    UsersDAO usersDAO;

    @Inject
    ThemeListsDAO themeListsDAO;

    @Inject
    NotificationsDAO notificationsDAO;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logIn() {
        List<User> users = usersDAO.getAll();

        Optional<User> findUser = users.stream().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password)).findFirst();

        if ( !findUser.isPresent() ) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Login", new FacesMessage("Wrong credentials"));
            return "";
        } else {
            this.user = findUser.get();
            if ( this.user != null ) {
                this.notifications = notificationsBean.getNotificationsForUser(this.getUser());
                List<ThemeList> subscribedThemeLists = user.getSubscribedThemeLists();
                for(ThemeList stl : subscribedThemeLists)
                    checkedMap.put(stl.getId(), true);
            }
        }
        return "main.xhtml";
    }

    public String logOut() {
        this.user = null;
        return "index.xhtml";
    }

    private Map<Integer, Boolean> checkedMap = new HashMap<Integer, Boolean>();

    public Map<Integer, Boolean> getCheckedMap() {
        return checkedMap;
    }

    public List<ThemeList> getSubscribedList() {
        List<ThemeList> selectedList = new ArrayList<>();
        for(ThemeList entity : themeListsDAO.getAll())
            if(checkedMap.containsKey(entity.getId()) && checkedMap.get(entity.getId()))
                selectedList.add(entity);

        return selectedList;
    }

    private String newThemeListName;

    public String getNewThemeListName() {
        return newThemeListName;
    }

    public void setNewThemeListName(String newThemeListName) {
        this.newThemeListName = newThemeListName;
    }

    public void createNewThemeList() {
        List<ThemeList> themeLists = themeListsDAO.getAll();
        Optional<ThemeList> tl = themeLists.stream().filter(t -> t.getName().equals(newThemeListName)).findFirst();
        if ( tl.isPresent() ) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Create theme list", new FacesMessage("Theme list with given name already exists"));
            return;
        }

        ThemeList newTL = new ThemeList();
        newTL.setName(newThemeListName);
        themeListsDAO.add(newTL);
        newThemeListName = null;
    }

    public void updateSubscribedThemeLists() {
        user.setSubscribedThemeLists(getSubscribedList());
        usersDAO.edit(user);
    }

    public String redirect() {
        if ( this.user == null )
            return "index.xhtml";
        else
            return "main.xhtml";
    }

    @Inject
    NotificationsBean notificationsBean;

    private List<Notification> notifications;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void clearNotifications() throws Exception {
        this.notifications = new ArrayList<>();
    }
}
