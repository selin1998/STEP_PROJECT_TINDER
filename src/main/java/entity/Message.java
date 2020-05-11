package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private int messageId;
    private String userLoginFrom;
    private String userLoginTo;
    private String message;
    private LocalDateTime time;

    public Message(int messageId, String userLoginFrom, String userLoginTo, String message, LocalDateTime time) {
        this.messageId = messageId;
        this.userLoginFrom = userLoginFrom;
        this.userLoginTo = userLoginTo;
        this.message = message;
        this.time = time;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getUserLoginFrom() {
        return userLoginFrom;
    }

    public void setUserLoginFrom(String userLoginFrom) {
        this.userLoginFrom = userLoginFrom;
    }

    public String getUserLoginTo() {
        return userLoginTo;
    }

    public void setUserLoginTo(String userLoginTo) {
        this.userLoginTo = userLoginTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
