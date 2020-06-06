package org.tinder.step;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.step.filter.IsAnybodyLoggedFilter;
import org.tinder.step.filter.LoginFilter;
import org.tinder.step.filter.RegisterFilter;
import org.tinder.step.servlet.*;
import org.tinder.step.util.TemplateEngine;

import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;
import java.util.EnumSet;

public class TinderApp {

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    public static void main(String[] args) throws Exception {


        // http://tinder-app-step-project.herokuapp.com/login


        TemplateEngine engine = new TemplateEngine();
        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new MainPageServlet()), "/");
        handler.addServlet(new ServletHolder(new LoginServlet(engine)), "/login");
        handler.addServlet(new ServletHolder(new LogoutServlet(engine)), "/logout");
        handler.addServlet(new ServletHolder(new UserServlet(engine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(engine)), "/liked");
        handler.addServlet(new ServletHolder(new MessageServlet(engine)), "/messages/*");
        handler.addServlet(RegisterServlet.class,"/signup").getRegistration().setMultipartConfig(new MultipartConfigElement(""));

        handler.addFilter(new FilterHolder(new LoginFilter(engine)), "/login", ft);
        handler.addFilter(new FilterHolder(new RegisterFilter(engine)), "/signup", ft);
        handler.addFilter(IsAnybodyLoggedFilter.class, "/users", ft);
        handler.addFilter(IsAnybodyLoggedFilter.class, "/liked", ft);
        handler.addFilter(IsAnybodyLoggedFilter.class, "/messages/*", ft);

        server.setHandler(handler);
        server.start();
        server.join();

    }
}
