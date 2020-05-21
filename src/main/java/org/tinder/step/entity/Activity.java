package org.tinder.step.entity;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Activity {
    private int user_id;
//    private String logout_time;
    private ZonedDateTime logout_time;

    public Activity(int user_id, ZonedDateTime logout_time) {
        this.user_id = user_id;
        this.logout_time = logout_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public ZonedDateTime getLogout_time() {
        return logout_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return getUser_id() == activity.getUser_id() &&
                Objects.equals(getLogout_time(), activity.getLogout_time());
    }

    @Override
    public String toString() {
        return "Activity{" +
                "user_id=" + user_id +
                ", logout_time=" + logout_time +
                '}';
    }
}
