package service;



import dao.DAO;
import dao.MessageDAO;
import entity.Message;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MessageService {

    DAO<Message> daoMsg=new MessageDAO();

    public MessageService() throws SQLException {
    }

    public List<Message> getMessagesForCurrentChat(int user_id_from,int user_id_to){
        Predicate<Message>m1=msg->msg.getUser_id_from()==user_id_from;
        Predicate<Message>m2=msg-> msg.getUser_id_to()==user_id_to;
        Predicate<Message>m3=msg->msg.getUser_id_from()==user_id_to;
        Predicate<Message>m4=msg-> msg.getUser_id_to()==user_id_from;

        return daoMsg.getAllBy(m1.and(m2).or(m3.and(m4))).stream().collect(Collectors.toList());
    }

    public void add(Message msg) throws SQLException {
        daoMsg.add(msg);
    }



}