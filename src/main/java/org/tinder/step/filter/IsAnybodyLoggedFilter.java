package org.tinder.step.filter;

import org.tinder.step.service.CookiesService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IsAnybodyLoggedFilter implements HttpFilter {  //implements Filter
    CookiesService cservice;

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        cservice = new CookiesService(request, response);
        if (cservice.getCookie() == null) {
            response.sendRedirect("/login");
        } else chain.doFilter(request, response);
    }

}
