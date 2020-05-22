package org.tinder.step.servlet;

import org.tinder.step.entity.Activity;
import org.tinder.step.entity.User;
import org.tinder.step.service.ActivityService;
import org.tinder.step.service.CookiesService;
import org.tinder.step.service.UserService;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class LogoutServlet extends HttpServlet {
    private TemplateEngine engine;
    private ActivityService activityService;
    CookiesService cookiesService;
    int loggedUserId;

    public LogoutServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        activityService=new ActivityService();
    }


//    @lombok.SneakyThrows     //--maybe later
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService= new CookiesService(req, resp);

        //add logout_time
        try {
            loggedUserId=cookiesService.getCookieValue().orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));

        Activity activity=new Activity(loggedUserId,zonedDateTimeNow);

        activityService.addLogout_time(activity);

        //remove cookies for logout
        cookiesService.removeCookie();
        resp.sendRedirect("/login");
    }

}
