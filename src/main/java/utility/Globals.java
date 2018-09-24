package utility;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import execution.guice.GuiceModule;

import java.io.IOException;
import java.util.Properties;

public class Globals {
    private static Injector guiceInjector = null;

    public static Injector getGuiceInjector() {
        if (guiceInjector == null) {
            guiceInjector = Guice.createInjector(Stage.PRODUCTION,
                    new GuiceModule());
        }

        return guiceInjector;
    }

    public static Properties initializeConfig() {
        Properties prop = new Properties();

        try {
            prop.load(Globals.class.getClassLoader().
                    getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
