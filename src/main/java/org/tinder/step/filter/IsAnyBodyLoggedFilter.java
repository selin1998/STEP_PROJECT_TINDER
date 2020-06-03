package org.tinder.step.filter;

import org.tinder.step.service.CookiesService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IsAnyBodyLoggedFilter implements HttpFilter {  //implements Filter
    CookiesService cservice;

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        cservice = new CookiesService(request, response);
        if (cservice.getCookie() == null) {
            response.sendRedirect("/login");
        } else chain.doFilter(request, response);
    }
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        cservice=new CookiesService(req,resp);
//        if(cservice.getCookie()==null){
//            resp.sendRedirect("/login");
//        }
//        else filterChain.doFilter(req,resp);
//
//    }

}
