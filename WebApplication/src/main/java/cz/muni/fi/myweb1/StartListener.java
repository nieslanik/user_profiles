package cz.muni.fi.myweb1;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent ev) {
        ServletContext servletContext = ev.getServletContext();
        servletContext.log("starting application ");
        //initialize your app here ...
        servletContext.setAttribute("mydb", "value");
    }

    @Override
    public void contextDestroyed(ServletContextEvent ev) {
    }
}
