import filter.HttpFilter;
import filter.LoginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;
import util.TemplateEngine;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class TinderApp {

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    public static void main(String[] args) throws Exception {
            Server server=new Server(HerokuEnv.port());
            TemplateEngine engine=new TemplateEngine();
            ServletContextHandler handler=new ServletContextHandler();
      //      handler.addServlet(new ServletHolder(new MainPageServlet()),"/");
            handler.addServlet(new ServletHolder(new LoginServlet(engine)),"/login");
            handler.addServlet(new ServletHolder(new RegisterServlet(engine)),"/signup");
            handler.addServlet(new ServletHolder(new LogoutServlet()),"/logout");
            handler.addServlet(new ServletHolder(new UserServlet(engine)),"/users");
            handler.addServlet(new ServletHolder(new LikedServlet(engine)),"/liked");
            handler.addServlet(new ServletHolder(new MessageServlet(engine)),"/messages/*");
            handler.addFilter(new FilterHolder(new LoginFilter(engine)), "/login/*", ft);
            handler.addFilter(HttpFilter.class,"/users",ft);
           handler.addFilter(HttpFilter.class,"/liked",ft);
           handler.addFilter(HttpFilter.class,"/messages/*",ft);


            server.setHandler(handler);
            server.start();
            server.join();

    }
}
