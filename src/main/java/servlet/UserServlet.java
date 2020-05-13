package servlet;

import dao.DAO;
import dao.UserDAO;
import db.DatabaseConnection;
import entity.User;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class UserServlet extends HttpServlet {
    private final TemplateEngine engine;
    DatabaseConnection db=new DatabaseConnection();
    Connection con=db.connect();
    DAO<User> dao=new UserDAO(con);
    int id=1;
    int currentUserId=5;

    public UserServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        User user=dao.get(id);
        data.put("user",user);
        engine.render("like-page.ftl",data,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if( (req.getParameter("like")!=null ||req.getParameter("dislike")!=null) && id<dao.getMaxId(currentUserId) ){
            id++;
            doGet(req,resp);
        }
        else{
            resp.sendRedirect("/liked");
        }



    }
}
