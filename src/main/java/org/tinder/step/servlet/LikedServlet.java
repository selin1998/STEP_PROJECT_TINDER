package org.tinder.step.servlet;

import lombok.extern.log4j.Log4j2;
import org.tinder.step.entity.Activity;
import org.tinder.step.entity.User;
import org.tinder.step.service.ActivityService;
import org.tinder.step.service.CookiesService;
import org.tinder.step.service.LikesService;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;
    LikesService likeService;
    CookiesService cookiesService;
    ActivityService activityService;

    int loggedUserId;

    public LikedServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        likeService=new LikesService();
        activityService=new ActivityService();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService=new CookiesService(req,resp);

        HashMap<String, Object> liked = new HashMap<>();
        try {
            loggedUserId=cookiesService.getCookieValue().orElseThrow(Exception::new);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        List<User> allLikedUsers = likeService.getAllLikedUsers(loggedUserId);
        
        List<Activity> allLikedUsersLogoutTime = activityService.getAllLikedUsersLogoutTime(loggedUserId);
        liked.put("likedlist",allLikedUsers);
        liked.put("lastlogin",allLikedUsersLogoutTime);
        engine.render("people-list.ftl",liked,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
