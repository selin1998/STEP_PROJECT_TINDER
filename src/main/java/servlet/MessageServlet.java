package servlet;

import entity.Message;
import entity.User;
import service.CookiesService;
import service.MessageService;
import service.UserService;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class MessageServlet extends HttpServlet {
    private final TemplateEngine engine;
    MessageService mservice=new MessageService();
    UserService uservice=new UserService();
    CookiesService cookiesService;
    int loggedUserId;

    public MessageServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService=new CookiesService(req,resp);

        try {
            loggedUserId=cookiesService.getCookieValue().orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> data = new HashMap<>();
        String path = req.getPathInfo();
        int user_id_to = Integer.parseInt(path.substring(1));

        List<Message> archiveChat = mservice.getMessagesForCurrentChat(loggedUserId, user_id_to);
        User targetUser = uservice.getById(user_id_to);
        data.put("messages",archiveChat);
        data.put("targetUser",targetUser);
        data.put("loggedUserId",loggedUserId);
        engine.render("chat.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        int user_id_to = Integer.parseInt(path.substring(1));
        LocalDateTime time=LocalDateTime.now();
        mservice.add(new Message(loggedUserId,user_id_to,req.getParameter("input"),time));
        doGet(req,resp);

    }
}
