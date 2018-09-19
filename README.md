# Jetty, Jersey and Guice Starter Kit

Jetty 9.4, Jersey 2.7 and Guice 4.2.0 starter kit with SSL support and guice-hk2-bridge.

# How to run
Clone && Run with maven by executing
```
mvn clean install exec:java
```

Go to http://localhost:8080/msg and you will see "My message" string.

# How to configure SSL
1) Put your JKS files (cert and trusted) in /resources/certificates folder,
you can see my self-singed certificates in the [certificates](/src/main/resources/certificates/) folder for your understanding
2) Update the path to those certificates in /utility/SSLContextHandler.java class
3) Update the password to those certificates in /resources/config.properties
4) Done

# Contribution
 - Fork
 - Code