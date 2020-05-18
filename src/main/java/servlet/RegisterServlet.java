package servlet;

import dao.UserDAO;
import entity.User;
import service.CookiesService;
import service.UserService;
import util.TemplateEngine;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class RegisterServlet extends HttpServlet {
    private TemplateEngine engine;
    private UserService userService = new UserService();

    public RegisterServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> data = new HashMap<>();
        data.put("Name", "Name");
        data.put("Surname", "Surname");
        data.put("photoLink", "photolink");
        data.put("Job", "Job");
        data.put("Email", "Email");
        data.put("Password", "Password");
        data.put("message", "Please sign in");

        engine.render("register.ftl", data, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("Name");
        String surname = req.getParameter("Surname");
        String photoLink = req.getParameter("photolink");
        String job = req.getParameter("Job");
        String login = req.getParameter("Email");
        String password = req.getParameter("Password");

        User user = new User(login, password, name, surname,job,photoLink);
        boolean result = userService.addUser(user);

        CookiesService cookiesService = new CookiesService(req, resp);
        if (result == true) {
            cookiesService.addCookie(userService.getUserId(login, password));
        } else {
            new SQLException("Something wrong.User can not be added!");
        }

        resp.sendRedirect("/users");
    }
}
