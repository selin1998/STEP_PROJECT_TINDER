package entity;

import java.util.Objects;

public class User  {
    private int user_id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String photoLink;
    private String job;

    public User(int user_id, String login, String password, String name, String surname, String photoLink, String job) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.photoLink = photoLink;
        this.job = job;
    }

    public User(String login, String password, String name, String surname, String job, String photoLink) {
        this(login,password,name,surname,job);
        this.photoLink = photoLink;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User( String login, String password,String name, String surname, String job) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.job=job;

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

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

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoUrl) {
        this.photoLink = photoUrl;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", photoUrl='" + photoLink + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
