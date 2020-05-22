package org.tinder.step.Converter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public String DateToString(ZonedDateTime dateTime){
        return dateTime.format(formatter);
    }
}
