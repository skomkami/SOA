package app;

import app.dao.EntriesDAO;
import app.dao.UsersDAO;
import app.message.NotifyMessage;
import app.model.Entry;
import app.model.Theme;
import app.model.ThemeList;
import app.model.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named("themeBean")
@SessionScoped
public class ThemeBean implements Serializable {

    @Inject
    NotificationsBean notificationsBean;

    private void sendMessage() {
        try {
            String themeListName = theme.getThemeList().getName();
            String themeName = theme.getName();
            ArrayList<String> usersToNotify = Arrays.stream(notifyUsers)
                    .map(User::getLogin)
                    .collect(Collectors.toCollection(ArrayList::new));

            if ( usersToNotify.isEmpty() ) {
                usersToNotify = getUsersSubscribingThemeList()
                        .stream()
                        .map(User::getLogin)
                        .collect(Collectors.toCollection(ArrayList::new));
            }

            NotifyMessage notifyMessage = new NotifyMessage(themeListName, themeName, usersToNotify);

            notificationsBean.sendMessage(notifyMessage);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Inject
    ForumBean forumBean;

    @Inject
    EntriesDAO entriesDAO;

    @Inject
    UsersDAO usersDAO;

    private Theme theme;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String goTo(Theme t) {
        theme = t;
        return "theme.xhtml";
    }

    private User[] notifyUsers;

    public User[] getNotifyUsers() {
        return notifyUsers;
    }

    public void setNotifyUsers(User[] notifyUsers) {
        this.notifyUsers = notifyUsers;
    }

    private String entryContent;

    public String getEntryContent() {
        return entryContent;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }

    public void createEntry() throws Exception {
        if ( forumBean.getUser() != null ) {
            Entry newEntry = new Entry();
            newEntry.setAuthor(forumBean.getUser());
            newEntry.setContent(entryContent);
            newEntry.setTheme(this.theme);
            entriesDAO.add(newEntry);
            entryContent = null;
            sendMessage();
        } else {
            throw new Exception("Operation not allowed");
        }
    }

    public List<User> getUsersSubscribingThemeList() {
        ThemeList tl = this.theme.getThemeList();
        return usersDAO.getUsersSubscribingThemeList(tl);
    }

    public List<Entry> getAllEntries() throws Exception {
        if( this.theme == null ) {
            throw new Exception("Not allowed");
        }

        return entriesDAO
                .getAll()
                .stream()
                .filter(e -> e.getTheme().getId() == this.theme.getId())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public String redirectIfThemeEmpty() {
        if ( this.theme == null )
            return "theme-list.xhtml";
        else
            return "";
    }
}
