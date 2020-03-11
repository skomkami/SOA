package model;

public class Post {
    private String userName;
    private String userEmail;
    private String comment;

    public Post(String userName, String userEmail, String comment) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getComment() {
        return comment;
    }

}
