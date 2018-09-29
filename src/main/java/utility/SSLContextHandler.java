package utility;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.*;

public class SSLContextHandler {

    @Inject
    @Named("certPass")
    private String certPass;

    @Inject
    @Named("trustPass")
    private String trustPass;

    @Inject
    @Named("certPath")
    private String certPath;

    @Inject
    @Named("trustPath")
    private String trustPath;

    private KeyStore keyStore;

    private KeyStore trustStore;

    public SSLContextHandler() throws Exception {
        Globals.getGuiceInjector().injectMembers(this);

        this.keyStore = this.loadStore(SSLContextHandler.class.getClassLoader().getResourceAsStream(this.certPath),
                this.certPass.toCharArray());

        this.trustStore = this.loadStore(SSLContextHandler.class.getClassLoader().getResourceAsStream(this.trustPath),
                this.trustPass.toCharArray());
    }

    private KeyStore loadStore(InputStream storeFile, char[] password) throws Exception {
        KeyStore ks;

        try (InputStream stream = storeFile) {
            ks = KeyStore.getInstance("JKS");
            ks.load(stream, password);
        }

        return ks;
    }

    public SSLContext generateSSLContext() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        SSLContext sslcontext = SSLContext.getInstance("TLS");

        KeyManagerFactory kmf =
                KeyManagerFactory.getInstance("SunX509");
        kmf.init(this.keyStore, this.certPass.toCharArray());

        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance("SunX509");
        tmf.init(this.trustStore);

        sslcontext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());

        return sslcontext;
    }

}
