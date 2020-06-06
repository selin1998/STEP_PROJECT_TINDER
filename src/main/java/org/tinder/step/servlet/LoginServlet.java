package org.tinder.step.servlet;
import lombok.SneakyThrows;
import org.tinder.step.service.CookiesService;
import org.tinder.step.service.UserService;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private final TemplateEngine engine;
    private UserService usersService=new UserService();
    private CookiesService cookiesService;

    public LoginServlet(TemplateEngine engine)  {
        this.engine = engine;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {


        HashMap<String, Object> data = new HashMap<>();
        data.put("Email","Email");
        data.put("message", "Please, sign in");
        engine.render("login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("Email");
        String password = req.getParameter("Password");
        cookiesService = new CookiesService(req, resp);
        cookiesService.addCookie(usersService.getUserId(login,password));
        cookiesService.addNewUserMark(usersService.getUserId(login,password));
        resp.sendRedirect("/users");
    }
}
