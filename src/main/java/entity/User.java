package entity;

import java.util.Objects;

public class User  {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String photoUrl;
    private String job;

    public User(String name, String surname, String login, String password,String job, String photoUrl) {
        this(name,surname,login,password,job);
        this.photoUrl = photoUrl;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public User(String name, String surname, String login, String password, String job) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.job=job;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

}
