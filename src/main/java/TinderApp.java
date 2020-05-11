import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TinderApp {

    public static void main(String[] args) throws Exception {
            Server server=new Server(9000);
            ServletContextHandler handler=new ServletContextHandler();
            handler.addServlet(new ServletHolder(new LoginServlet()),"/login");
            handler.addServlet(new ServletHolder(new RegisterServlet()),"/login/signup");
            handler.addServlet(new ServletHolder(new LogoutServlet()),"/logout");
            handler.addServlet(new ServletHolder(new UserServlet()),"/users");
            handler.addServlet(new ServletHolder(new LikedServlet()),"/liked");
            handler.addServlet(new ServletHolder(new MessageServlet()),"/messages/*");

            // filters TODO


            server.setHandler(handler);
            server.start();
            server.join();

    }
}
