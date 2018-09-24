package execution.jersey;

import controllers.Message;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import utility.Globals;

import javax.inject.Inject;

class JerseyConfiguration extends ResourceConfig {

    @Inject
    public JerseyConfiguration(ServiceLocator serviceLocator) {
        // Guice injection bridge
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(Globals.getGuiceInjector());

        // Controllers
        register(Message.class);
    }
}