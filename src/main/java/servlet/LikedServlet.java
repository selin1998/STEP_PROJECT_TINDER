package servlet;

import db.DatabaseConnection;
import service.LikesService;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;
    DatabaseConnection db=new DatabaseConnection();
    Connection con=db.connect();
    LikesService service;

    public LikedServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        service=new LikesService(engine);
    }

    int currentUserId=7;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> liked = new HashMap<>();
        liked.put("likedlist",service.getAllLikedUsersIds(currentUserId));
        engine.render("people-list.ftl",liked,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
