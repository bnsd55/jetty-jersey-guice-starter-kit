package execution.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import utility.Globals;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        Names.bindProperties(binder(), Globals.initializeConfig());
    }
}
