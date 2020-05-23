package org.tinder.step.entity;

import lombok.*;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Activity {
    private int user_id;
    private ZonedDateTime logout_time;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy");

//    public Activity(int user_id, ZonedDateTime logout_time) {
//        this.user_id = user_id;
//        this.logout_time = logout_time;
//    }
//
//    public int getUser_id() {
//        return user_id;
//    }
//
//    public ZonedDateTime getLogout_time() {
//        return logout_time;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Activity)) return false;
//        Activity activity = (Activity) o;
//        return getUser_id() == activity.getUser_id() &&
//                Objects.equals(getLogout_time(), activity.getLogout_time());
//    }

    public String getLogout_time_String(){
        return formatter.format(logout_time);
    }

    public String howMuchTimeAgo(){
        Period p = Period.between( logout_time.toLocalDate() , ZonedDateTime.now(ZoneId.of("UTC")).toLocalDate() );
        int days=p.getDays();
        return (days==0)?"Today":(days==1)?"Yesterday":days+" days ago";
    }

//    @Override
//    public String toString() {
//        return "Activity{" +
//                "user_id=" + user_id +
//                ", logout_time=" + logout_time +
//                '}';
//    }
}
