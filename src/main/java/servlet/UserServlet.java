package servlet;

import dao.DAO;
import dao.LikesDAO;
import dao.UserDAO;
import db.DatabaseConnection;
import entity.Like;
import entity.User;
import service.LikesService;
import service.UserService;
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
    UserService serviceUser=new UserService();
    LikesService serviceLike=new LikesService();
    int id=1;
    int currentUserId=7;

    public UserServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        User user=serviceUser.getById(id);
        data.put("user",user);
        engine.render("like-page.ftl",data,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isThereClick=(req.getParameter("like")!=null ||req.getParameter("dislike")!=null);

        if(req.getParameter("like")!=null){
            serviceLike.add(new Like(currentUserId,id));
        }
        if( isThereClick && id<serviceUser.getMaxId() ){
            id++;
            doGet(req,resp);
        }
        else{
            resp.sendRedirect("/liked");
        }

    }
}
