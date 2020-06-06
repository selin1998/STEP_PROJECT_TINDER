package org.tinder.step.servlet;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
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
import java.util.Optional;

@Log4j2
public class UserServlet extends HttpServlet {
    private final TemplateEngine engine;
    UserService serviceUser;
    LikesService serviceLike;
    int idx;

    public UserServlet(TemplateEngine engine) {
        this.engine = engine;
        serviceUser = new UserService();
        serviceLike = new LikesService();

    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        CookiesService cookiesService = new CookiesService(req, resp);
        int loggedUserId = cookiesService.getCookieValue().orElseThrow(Exception::new);
        if (cookiesService.isUserNewlyLogged()) {
            idx = 0;
            cookiesService.removeNewUserMark();
        }

        List<Integer> allUserIds = serviceUser.getAllUserIds(loggedUserId);

        int last_idx = allUserIds.size();

        HashMap<String, Object> data = new HashMap<>();

        Optional<User> user;
        if (idx < last_idx) {
            user = serviceUser.getById(allUserIds.get(idx));
            data.put("user", user.get());
            engine.render("like-page.ftl", data, resp);
            idx++;
        } else {
            idx = 0;
            resp.sendRedirect("/liked");

        }

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        CookiesService cookiesService = new CookiesService(req, resp);

        int loggedUserId = cookiesService.getCookieValue().orElseThrow(Exception::new);
        int userIdOfImage = Integer.parseInt(req.getParameter("user_id"));

        if (req.getParameter("like") != null) {

            serviceLike.add(new Like(loggedUserId, userIdOfImage));
            log.info(loggedUserId + " likes " + userIdOfImage);

        }
        if (req.getParameter("dislike") != null) {

            serviceLike.remove(loggedUserId, userIdOfImage);
            log.info(loggedUserId + " dislikes " + userIdOfImage);

        }

        doGet(req, resp);

    }


}
