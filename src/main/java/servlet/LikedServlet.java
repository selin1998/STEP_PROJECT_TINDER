package servlet;
import entity.User;
import service.LikesService;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;
    LikesService service;

    public LikedServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        service=new LikesService();
    }

    int currentUserId=7;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> liked = new HashMap<>();
        List<User> allLikedUsers = service.getAllLikedUsers(currentUserId);
        liked.put("likedlist",allLikedUsers);
        engine.render("people-list.ftl",liked,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
