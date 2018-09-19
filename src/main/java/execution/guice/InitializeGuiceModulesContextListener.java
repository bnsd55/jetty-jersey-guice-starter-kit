package execution.guice;


import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import utility.Globals;

import javax.servlet.ServletContextEvent;
import java.util.Locale;

public class InitializeGuiceModulesContextListener extends GuiceServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Locale.setDefault(Locale.ENGLISH);
        super.contextInitialized(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        return Globals.getGuiceInjector();
    }
}