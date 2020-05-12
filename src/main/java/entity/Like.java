package entity;

public class Like {
    private String login_from;
    private String login_to;

    public Like(String login_from, String login_to) {
        this.login_from = login_from;
        this.login_to = login_to;
    }

    public String getLogin_from() {
        return login_from;
    }

    public void setLogin_from(String login_from) {
        this.login_from = login_from;
    }

    public String getLogin_to() {
        return login_to;
    }

    public void setLogin_to(String login_to) {
        this.login_to = login_to;
    }
}
