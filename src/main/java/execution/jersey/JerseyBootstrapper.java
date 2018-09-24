package execution.jersey;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.servlet.GuiceFilter;
import execution.guice.InitializeGuiceModulesContextListener;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;
import utility.Globals;
import utility.SSLContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JerseyBootstrapper {

    @Inject
    @Named("useSSL")
    boolean useSSL;

    @Inject
    @Named("serverPort")
    int port;

    @Inject
    @Named("serverSecurePort")
    int securePort;

    private Server jettyServer;

    public JerseyBootstrapper() {
        Globals.getGuiceInjector().injectMembers(this);
    }

    /**
     * Init jersey server from code instead of a web.xml
     */
    public void setupServer() {
        // Create our server according to SSL configuration
        if (!this.useSSL) {
            this.jettyServer = new Server(this.port);
        } else {
            this.jettyServer = new Server();
            HttpConfiguration https = new HttpConfiguration();
            https.addCustomizer(new SecureRequestCustomizer());
            https.setSecureScheme("https");
            SslContextFactory sslContextFactory = new SslContextFactory();
            SSLContextHandler SSLContextHandler = Globals.getGuiceInjector().getInstance(SSLContextHandler.class);

            try {
                sslContextFactory.setSslContext(SSLContextHandler.generateSSLContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            sslContextFactory.setNeedClientAuth(true);
            ServerConnector sslConnector = new ServerConnector(this.jettyServer, new SslConnectionFactory(sslContextFactory,
                    HttpVersion.HTTP_1_1.asString()), new HttpConnectionFactory(https));
            sslConnector.setPort(this.securePort);

            this.jettyServer.addConnector(sslConnector);
        }

        HandlerCollection handlerCollection = new HandlerCollection();

        // WebApp handler
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(this.jettyServer);

        // Guice filter
        webAppContext.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

        ServletHolder holder = new ServletHolder(ServletContainer.class);
        holder.setInitParameter("javax.ws.rs.Application", JerseyConfiguration.class.getCanonicalName());

        webAppContext.addServlet(holder, "/*");
        webAppContext.setResourceBase("/");
        webAppContext.setContextPath("/");
        webAppContext.addEventListener(new InitializeGuiceModulesContextListener());

        // Add all handlers
        handlerCollection.addHandler(webAppContext);

        // Set all handlers to jetty
        this.jettyServer.setHandler(handlerCollection);
    }

    public void startServer() throws Exception {
        this.jettyServer.start();
        this.jettyServer.join();
    }

    public void stopServer() throws Exception {
        this.jettyServer.stop();
    }

    public void destroyServer() {
        this.jettyServer.destroy();
    }
}
