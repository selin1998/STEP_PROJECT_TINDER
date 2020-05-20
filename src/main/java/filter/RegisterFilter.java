package filter;


import dao.UserDAO;
import entity.User;
import org.eclipse.jetty.http.HttpMethod;
import service.UserService;
import util.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

 public class RegisterFilter implements Filter  {

    private TemplateEngine engine;
    private UserDAO daoUser=new UserDAO();
    private UserService usersService=new UserService(daoUser);

    public RegisterFilter(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }
        HashMap<String, Object> data = new HashMap<>();

        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            try {
//                String name = req.getParameter("Name");
//                String surname = req.getParameter("Surname");
//                String photoLink = req.getParameter("photolink");
//                String job = req.getParameter("Job");
                String login = req.getParameter("Email");
                String password = req.getParameter("Password");
                User user=new User(login,password);

                if (usersService.checkUserByLogin(user)) {
                    throw new Exception("Such user already exist! Please check your info.");
                }
                chain.doFilter(request, response);  //req resp ???
            } catch (Exception e) {
                data.put("message", e.getMessage());
                engine.render("error_message.ftl", data,(HttpServletResponse) response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
