package org.tinder.step.filter;

import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.http.HttpMethod;
import org.tinder.step.entity.User;
import org.tinder.step.service.UserService;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@Log4j2
public class LoginFilter implements HttpFilter {   //implements Filter

    private TemplateEngine engine = new TemplateEngine();

    private UserService usersService=new UserService();

    public LoginFilter(TemplateEngine engine) throws IOException, SQLException {
        this.engine=engine;
    }

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HashMap<String, Object> data = new HashMap<>();

        if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
            try {
                String login = request.getParameter("Email");
                String password = request.getParameter("Password");

                User user=new User(login,password);

                if (!usersService.checkUser(user)) {
                    log.error(new Exception("Incorrect login or password"));
                    response.sendRedirect("/login");
//                    throw new Exception("Incorrect login or password");
                }
                chain.doFilter(request, response);
            } catch (Exception e) {
                log.error(e.getMessage());
                response.sendRedirect("/login");
//                data.put("message", e.getMessage());
//                engine.render("error_message.ftl", data,(HttpServletResponse) response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }



//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req;
//        if (request instanceof HttpServletRequest) {
//            req = (HttpServletRequest) request;
//        } else {
//            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
//        }
//
//        HashMap<String, Object> data = new HashMap<>();
//
//        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
//            try {
//                String login = req.getParameter("Email");
//                String password = req.getParameter("Password");
//
//                User user=new User(login,password);
//
//                if (!usersService.checkUser(user)) {
//                    throw new Exception("Incorrect login or password");
//                }
//                chain.doFilter(request, response);
//            } catch (Exception e) {
//                log.error(e.getMessage());
////                data.put("message", e.getMessage());
////                engine.render("error_message.ftl", data,(HttpServletResponse) response);
//            }
//        } else {
//            chain.doFilter(request, response);
//        }
//
//    }


}
