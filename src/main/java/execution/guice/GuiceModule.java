package execution.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.servlet.ServletModule;
import controllers.Message;
import utility.Globals;

import java.util.Properties;

public class GuiceModule extends AbstractModule {

    private Properties config;

    public GuiceModule() {
        this.config = Globals.initializeConfig();
    }

    @Override
    protected void configure() {
        bind(Message.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    @Named("serverSecurePort")
    int getServerSecurePort() {
        return Integer.parseInt(config.getProperty("serverSecurePort"));
    }

    @Provides
    @Singleton
    @Named("serverPort")
    int getServerPort() {
        return Integer.parseInt(config.getProperty("serverPort"));
    }

    @Provides
    @Singleton
    @Named("useSSL")
    boolean getUseSSL() {
        return Boolean.parseBoolean(config.getProperty("useSSL"));
    }

    @Provides
    @Singleton
    @Named("certPass")
    String getCertPassL() {
        return config.getProperty("certPass");
    }

    @Provides
    @Singleton
    @Named("trustPass")
    String getTrustPass() {
        return config.getProperty("trustPass");
    }
}
