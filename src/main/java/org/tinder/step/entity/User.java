package org.tinder.step.entity;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
//@Data
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class User  {


    private int user_id;
    @NonNull
    private String login;
    @NonNull
    private String password;
    private String name;
    private String surname;
    private String job;
    private String photoLink;



    public User( String login, String password,String name, String surname,String job, String photoLink) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.job=job;
        this.photoLink = photoLink;
    }

//    public User( int user_id,String login, String password,String name, String surname,String job, String photoLink) {
//
//        this(login,password,name,surname,job,photoLink);
//        this.user_id=user_id;
//    }


//
//    public User(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }

//    public String getJob() {
//        return job;
//    }
//
//    public void setJob(String job) {
//        this.job = job;
//    }
//
//    public int getUser_id() {
//        return user_id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPhotoLink() {
//        return photoLink;
//    }
//
//    public void setPhotoLink(String photoUrl) {
//        this.photoLink = photoUrl;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return login.equals(user.login) &&
//                password.equals(user.password);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(login, password);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", photoUrl='" + photoLink + '\'' +
//                ", job='" + job + '\'' +
//                '}';
//    }
}
