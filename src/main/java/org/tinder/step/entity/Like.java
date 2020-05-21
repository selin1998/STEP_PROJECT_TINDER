package org.tinder.step.entity;

public class Like {
    private int user_id_from;
    private int user_id_to;

    public Like(int user_id_from, int user_id_to) {
        this.user_id_from = user_id_from;
        this.user_id_to = user_id_to;
    }

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
}
