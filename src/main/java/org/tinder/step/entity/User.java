package org.tinder.step.entity;

import lombok.*;

import java.util.Base64;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
//@Data
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class User {


    private int user_id;
    @NonNull
    private String login;
    @NonNull
    private String password;
    private String name;
    private String surname;
    private String job;
    private byte[] photoLink;


    public User(String login, String password, String name, String surname, String job, byte[] photoLink) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.job = job;
        this.photoLink = photoLink;
    }

    public String getImageAsBase64() {
        byte[] encoded = Base64.getEncoder().encode(photoLink);
        String imgDataAsBase64 = new String(encoded);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        return imgAsBase64;
    }
}
