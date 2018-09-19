# Jetty, Jersey and Guice Starter Kit

Jetty 9.4, Jersey 2.7 and Guice 4.2.0 starter kit with SSL support and guice-hk2-bridge.

# How to run
Clone && run with maven by executing
```
mvn clean install exec:java
```

# How to configure SSL
1) Put your JKS files (cert and trusted) in /resources/certificates folder
2) Update the path to those certificates in /utility/SSLContextHandler.java class
3) Update the password to those certificates in /resources/config.properties
4) Done

# Contribution
 - Fork
 - Code