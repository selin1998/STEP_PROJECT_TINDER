package org.tinder.step.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class CookiesService {

    private final String COOKIE_NAME = "activeUser";
    private final String NEWLY_LOGGED_USER = "newUser";
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public CookiesService(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public Optional<Integer> getCookieValue() {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(COOKIE_NAME)) {
                return Optional.of(Integer.parseInt(cookie.getValue()));
            }
        }
        return Optional.empty();
    }

    public void addCookie(int id) {
        resp.addCookie(new Cookie(COOKIE_NAME, String.valueOf(id)));
    }

    public void addNewUserMark(int id){
        resp.addCookie(new Cookie(NEWLY_LOGGED_USER,String.valueOf(id)));
    }

    public void removeNewUserMark(){
        Arrays
                .stream(req.getCookies())
                .filter(c -> c.getName().equalsIgnoreCase(NEWLY_LOGGED_USER))
                .map(c -> new Cookie(c.getName(), c.getValue()) {{
                    setMaxAge(0);
                }})
                .forEach(resp::addCookie);
    }

    public boolean isUserNewlyLogged(){
        boolean result=false;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(NEWLY_LOGGED_USER)) {
                    result = true;
                }
            }
        }
        return result;
    }


    public void removeCookie() {
        Arrays
                .stream(req.getCookies())
                .filter(c -> c.getName().equalsIgnoreCase(COOKIE_NAME))
                .map(c -> new Cookie(c.getName(), c.getValue()) {{
                    setMaxAge(0);
                }})
                .forEach(resp::addCookie);
    }

    public Cookie getCookie() {
        Cookie result = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(COOKIE_NAME)) {
                    result = c;
                }
            }
        }
        return result;
    }


}