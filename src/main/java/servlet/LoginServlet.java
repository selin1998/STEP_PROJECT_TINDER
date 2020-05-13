package servlet;

import entity.User;
import org.eclipse.jetty.servlet.Source;
import service.CookiesService;
import service.UserService;
import util.TemplateEngine;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private final TemplateEngine engine=new TemplateEngine();
    private CookiesService cookiesService;
   // private final Connection connection;
   // private UserService usersService;

//    Connection connection
    public LoginServlet() throws IOException {
       // this.connection=connection;
        //this.usersService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> fields = new ArrayList<>();
        fields.add("Email");

        HashMap<String, Object> data = new HashMap<>();
        data.put("fields", fields);
//        data.put("email", req.getParameter("email"));
        data.put("message", "Please sign in");
        data.put("root", "/login");
        engine.render("login.ftl", data, resp);

//        engine.render("login.ftl", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("Email");
        String password = req.getParameter("Password");

        //for add cookie
//        User user=new User(login,password);
//        cookiesService.addCookie(usersService.getUserId(user));

        resp.sendRedirect("/users");
    }
}
