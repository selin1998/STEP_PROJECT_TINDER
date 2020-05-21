package org.tinder.step.filter;

import org.tinder.step.service.CookiesService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpFilter implements Filter {
    CookiesService cservice;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        cservice=new CookiesService(req,resp);
        if(cservice.getCookie()==null){
            resp.sendRedirect("/login");
        }
        else filterChain.doFilter(req,resp);

    }

    @Override
    public void destroy() {

    }
}
