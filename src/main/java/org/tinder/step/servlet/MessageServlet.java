package org.tinder.step.servlet;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.tinder.step.entity.Message;
import org.tinder.step.entity.User;
import org.tinder.step.service.CookiesService;
import org.tinder.step.service.MessageService;
import org.tinder.step.service.UserService;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Log4j2
public class MessageServlet extends HttpServlet {
    private final TemplateEngine engine;
    MessageService mservice=new MessageService();
    UserService uservice=new UserService();
    CookiesService cookiesService;

    public MessageServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {

        cookiesService=new CookiesService(req,resp);


        int loggedUserId=cookiesService.getCookieValue().orElseThrow(Exception::new);

        HashMap<String, Object> data = new HashMap<>();

        String path = req.getPathInfo();

        int user_id_to = Integer.parseInt(path.substring(1));

        List<Message> archiveChat = mservice.getMessagesForCurrentChat(loggedUserId, user_id_to);
        User targetUser = uservice.getById(user_id_to).orElseThrow(Exception::new);
        User loggedUser = uservice.getById(loggedUserId).orElseThrow(Exception::new);

        data.put("messages",archiveChat);
        data.put("targetUser",targetUser);
        data.put("loggedUser",loggedUser);
        engine.render("chat.ftl",data,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {


        int loggedUserId=cookiesService.getCookieValue().orElseThrow(Exception::new);

        ZonedDateTime time=ZonedDateTime.now();
        String path = req.getPathInfo();
        int user_id_to = Integer.parseInt(path.substring(1));
        mservice.add(new Message(loggedUserId,user_id_to,req.getParameter("input"),time));
        doGet(req,resp);

    }
}
