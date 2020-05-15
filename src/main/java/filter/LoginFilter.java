package filter;

import dao.UserDAO;
import db.DatabaseConnection;
import entity.User;
import org.eclipse.jetty.http.HttpMethod;
import service.UserService;
import util.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginFilter implements Filter {

    private TemplateEngine engine = new TemplateEngine();
    private DatabaseConnection db=new DatabaseConnection();
    private Connection con=db.connect();
    private UserDAO daoUser=new UserDAO(con);
    private UserService usersService=new UserService(daoUser);

    public LoginFilter(TemplateEngine engine) throws IOException, SQLException {
        this.engine=engine;
    }


    public void init(FilterConfig filterConfig) throws ServletException {

    }

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
                String login = req.getParameter("Email");
                String password = req.getParameter("Password");
                User user = new User(login, password);

                if (!usersService.checkUser(user)) {
                    throw new Exception("Incorrect login or password");
                }
                chain.doFilter(request, response);
            } catch (Exception e) {
                data.put("message", e.getMessage());
//                data.put("rout","/login");
                engine.render("error_message.ftl", data,(HttpServletResponse) response);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    public void destroy() {

    }
}
