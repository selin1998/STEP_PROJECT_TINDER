import filter.LoginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;
import util.TemplateEngine;

import javax.servlet.DispatcherType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;

public class TinderApp {

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    public static void main(String[] args) throws Exception {
        Server server = new Server(9000);
        TemplateEngine engine = new TemplateEngine();
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new LoginServlet(engine)), "/login");
        handler.addServlet(new ServletHolder(new RegisterServlet()), "/login/signup");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        handler.addServlet(new ServletHolder(new UserServlet(engine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet()), "/liked");
        handler.addServlet(new ServletHolder(new MessageServlet()), "/messages/*");

        handler.addServlet(new ServletHolder(new LoginServlet(engine)), "/login/*");

        // filters
        handler.addFilter(new FilterHolder(new LoginFilter(engine)), "/login/*", ft);

        server.setHandler(handler);
        server.start();
        server.join();

    }
}
