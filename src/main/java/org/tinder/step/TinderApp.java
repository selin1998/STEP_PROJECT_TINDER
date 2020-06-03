package org.tinder.step;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.step.filter.IsAnyBodyLoggedFilter;
import org.tinder.step.filter.LoginFilter;
import org.tinder.step.filter.RegisterFilter;
import org.tinder.step.servlet.*;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


public class TinderApp {

    // http://tinder-step-project-ss.herokuapp.com/login
    // http://tinder-app-step-project.herokuapp.com/login

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    public static void main(String[] args) throws Exception {

        TemplateEngine engine = new TemplateEngine();
        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new MainPageServlet()), "/");
        handler.addServlet(new ServletHolder(new LoginServlet(engine)), "/login");
        handler.addServlet(new ServletHolder(new RegisterServlet(engine)), "/signup");
        handler.addServlet(new ServletHolder(new LogoutServlet(engine)), "/logout");
        handler.addServlet(new ServletHolder(new UserServlet(engine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(engine)), "/liked");
        handler.addServlet(new ServletHolder(new MessageServlet(engine)), "/messages/*");

        handler.addFilter(new FilterHolder(new LoginFilter(engine)), "/login", ft);
        handler.addFilter(new FilterHolder(new RegisterFilter(engine)), "/signup", ft);
        handler.addFilter(IsAnyBodyLoggedFilter.class, "/users", ft);
        handler.addFilter(IsAnyBodyLoggedFilter.class, "/liked", ft);
        handler.addFilter(IsAnyBodyLoggedFilter.class, "/messages/*", ft);


        server.setHandler(handler);
        server.start();
        server.join();

    }
}
