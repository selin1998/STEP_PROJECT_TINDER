package org.tinder.step.servlet;

import lombok.extern.log4j.Log4j2;
import org.tinder.step.entity.User;
import org.tinder.step.service.CookiesService;
import org.tinder.step.service.UserService;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
@Log4j2
public class RegisterServlet extends HttpServlet {
    private TemplateEngine engine=new TemplateEngine();
    private UserService userService = new UserService();

    public RegisterServlet() throws IOException {

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
        data.put("message", "Please, sign in");

        engine.render("register.ftl", data, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("Name");
        String surname = req.getParameter("Surname");
        Part photoLink = req.getPart("photolink");
        String job = req.getParameter("Job");
        String login = req.getParameter("Email");
        String password = req.getParameter("Password");

        // photoLink.getInputStream().readAllBytes(); -> since Java 9

        InputStream inputStream = photoLink.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        User user = new User(login, password, name, surname,job,buffer.toByteArray());
        boolean result = userService.addUser(user);

        CookiesService cookiesService = new CookiesService(req, resp);
        if (result) {
            cookiesService.addCookie(userService.getUserId(login, password));
            cookiesService.addNewUserMark(userService.getUserId(login, password));
        } else {
            log.error(new SQLException("Something wrong.User can not be added!"));
        }

        resp.sendRedirect("/users");
    }
}
