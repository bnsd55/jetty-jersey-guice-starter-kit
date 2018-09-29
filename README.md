# Jetty, Jersey and Guice Starter Kit

Jetty 9.4, Jersey 2.7 and Guice 4.2.0 starter kit with SSL support and guice-hk2-bridge.

# How to run
Clone && Run with maven by executing
```
mvn clean install exec:java
```

Go to http://localhost:8080/msg and you will see some running message.

# How to configure SSL
1) Put your JKS files (cert and trusted) wherever you want in /resources/ directory.
2) Update the path and password to those certificates in config.properties.
3) Update useSSL config property to True.
4) Run.

# Contribution
 - Fork
 - Code