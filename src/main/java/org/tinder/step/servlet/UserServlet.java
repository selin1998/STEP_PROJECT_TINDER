package org.tinder.step.servlet;
import org.tinder.step.entity.Like;
import org.tinder.step.entity.User;
import org.tinder.step.service.CookiesService;
import org.tinder.step.service.LikesService;
import org.tinder.step.service.UserService;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {
    private final TemplateEngine engine;
    UserService serviceUser=new UserService();
    LikesService serviceLike=new LikesService();
    CookiesService cookiesService;
    int loggedUserId;
    List<Integer> allUserIds;
    int i=0;
    public UserServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService=new CookiesService(req,resp);

        try {
            loggedUserId=cookiesService.getCookieValue().orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }

        allUserIds = serviceUser.getAllUserIds(loggedUserId);

        HashMap<String, Object> data = new HashMap<>();
        User user= null;
        try {
            user = serviceUser.getById(allUserIds.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.put("user",user);
        engine.render("like-page.ftl",data,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isThereClick=(req.getParameter("like")!=null ||req.getParameter("dislike")!=null);
        int userIdOfImage=allUserIds.get(i);
        int MaxUserId= Collections.max(allUserIds);
        if(req.getParameter("like")!=null){

                serviceLike.add(new Like(loggedUserId,userIdOfImage));

        }
        if(req.getParameter("dislike")!=null){
            serviceLike.remove(loggedUserId,userIdOfImage);
        }
        if( isThereClick && userIdOfImage<MaxUserId ){
            i++;
            doGet(req,resp);
        }
        else{
            i=0;
            resp.sendRedirect("/liked");

        }

    }


}
