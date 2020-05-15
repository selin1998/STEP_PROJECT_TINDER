package servlet;

import dao.DAO;
import dao.UserDAO;
import db.DatabaseConnection;
import entity.User;
import service.CookiesService;
import service.UserService;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private final TemplateEngine engine;
    private DatabaseConnection db=new DatabaseConnection();
    private Connection con=db.connect();
    private UserDAO daoUser=new UserDAO(con);
    private UserService usersService=new UserService(daoUser);
    private CookiesService cookiesService;

    public LoginServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> fields = new ArrayList<>();
        fields.add("Email");

        HashMap<String, Object> data = new HashMap<>();
        data.put("fields", fields);
        data.put("message", "Please sign in");
        data.put("root", "/login");
        engine.render("login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("Email");
        String password = req.getParameter("Password");

//        for add cookie
//        User user=new User(login,password);
       cookiesService = new CookiesService(req, resp);
       cookiesService.addCookie(usersService.getUserId(login,password));

        resp.sendRedirect("/users");
    }
}
