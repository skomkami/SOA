package model;

public class User {

    private String login;
    private String password;
//    private String name;
//    private String surname;

    public User(String login, String password/*, String name, String surname*/) {
        this.login = login;
        this.password = password;
//        this.name = name;
//        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (this.login == null) {
            if (other.login != null)
                return false;
        } else if (!this.login.equals(other.login))
            return false;
        if (this.password == null) {
            if (other.password != null)
                return false;
        } else if (!this.password.equals(other.password))
            return false;
        return true;
    }

}
