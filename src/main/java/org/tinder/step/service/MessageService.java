package org.tinder.step.service;


import org.tinder.step.dao.DAO;
import org.tinder.step.dao.MessageDAO;
import org.tinder.step.entity.Message;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MessageService {

    MessageDAO daoMsg;

    public MessageService() {
        daoMsg = new MessageDAO();
    }

    public List<Message> getMessagesForCurrentChat(int user_id_from, int user_id_to) {
        Comparator<Message> comparator = Comparator.comparing(msg -> msg.getTime().truncatedTo(ChronoUnit.MINUTES));
        return daoMsg.getMessagesForChat(user_id_from, user_id_to).stream().sorted(comparator).collect(Collectors.toList());
    }

    public void add(Message msg) {
        daoMsg.add(msg);
    }


}
