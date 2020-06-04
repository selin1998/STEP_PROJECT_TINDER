package org.tinder.step.servlet;

import lombok.SneakyThrows;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Log4j2
public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;
    LikesService likeService;
    CookiesService cookiesService;
    ActivityService activityService;

    public LikedServlet(TemplateEngine engine) {
        this.engine = engine;
        likeService = new LikesService();
        activityService = new ActivityService();

    }


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService = new CookiesService(req, resp);

        HashMap<String, Object> liked = new HashMap<>();

        int loggedUserId = cookiesService.getCookieValue().orElseThrow(Exception::new);

        List<User> allLikedUsers = likeService.getAllLikedUsers(loggedUserId).stream().map(i -> i.get()).collect(Collectors.toList());



        List<Activity> allLikedUsersLogoutTime = activityService.getAllLikedUsersLogoutTime(loggedUserId);
        liked.put("likedlist", allLikedUsers);
        liked.put("lastlogin", allLikedUsersLogoutTime);

        if (allLikedUsers.stream().noneMatch(i -> i.equals(Optional.empty())))
            engine.render("people-list.ftl", liked, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
