package org.tinder.step.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Like {
    private int user_id_from;
    private int user_id_to;

}
