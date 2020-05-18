package servlet;
import entity.User;
import service.CookiesService;
import service.LikesService;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;
    LikesService likeService;
    CookiesService cookiesService;
    int loggedUserId;

    public LikedServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        likeService=new LikesService();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService=new CookiesService(req,resp);

        HashMap<String, Object> liked = new HashMap<>();
        try {
            loggedUserId=cookiesService.getCookieValue().orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User> allLikedUsers = likeService.getAllLikedUsers(loggedUserId);
        liked.put("likedlist",allLikedUsers);
        engine.render("people-list.ftl",liked,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
