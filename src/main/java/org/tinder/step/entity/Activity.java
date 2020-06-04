package org.tinder.step.entity;

import lombok.*;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Activity {
    private int user_id;
    private ZonedDateTime logout_time;

    public String getLogout_time_String() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy");
        return formatter.format(logout_time);
    }

    public String howMuchTimeAgo() {
        Period p = Period.between(logout_time.toLocalDate(), ZonedDateTime.now(ZoneId.of("UTC")).toLocalDate());
        int days = p.getDays();
        return (days == 0) ? "Today" : (days == 1) ? "Yesterday" : days + " days ago";
    }

}
