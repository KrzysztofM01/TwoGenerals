package database;



public class User {
    private int id;
    private String login;
    private String password;
    private boolean hasAdmin = false;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasAdmin() {
        return hasAdmin;
    }

    public void setHasAdmin(boolean hasAdmin) {
        this.hasAdmin = hasAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", hasAdmin=" + hasAdmin +
                '}';
    }
}