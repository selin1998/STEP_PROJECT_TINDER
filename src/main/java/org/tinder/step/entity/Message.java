package org.tinder.step.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private int user_id_from;
    private int user_id_to;
    private String message;
    private LocalDateTime time;


    public int getUser_id_from() {
        return user_id_from;
    }

    public void setUser_id_from(int user_id_from) {
        this.user_id_from = user_id_from;
    }

    public int getUser_id_to() {
        return user_id_to;
    }

    public void setUser_id_to(int user_id_to) {
        this.user_id_to = user_id_to;
    }

    public Message(int user_id_from, int user_id_to, String message, LocalDateTime time) {
        this.user_id_from = user_id_from;
        this.user_id_to = user_id_to;
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeString() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public LocalDateTime getTime(){
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user_id_from=" + user_id_from +
                ", user_id_to=" + user_id_to +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
