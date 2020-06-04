package org.tinder.step.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Message {
    private int user_id_from;
    private int user_id_to;
    private String message;
    private ZonedDateTime time;

    public String getTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy");
        return formatter.format(time);
    }


}
