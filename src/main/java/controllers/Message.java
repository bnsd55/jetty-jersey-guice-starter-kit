package controllers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/msg")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Message {

    @Inject
    @Named("serverPort")
    private Integer port;

    @GET
    public String getMessage() {
        return "Hey!, I'm running on port: " + this.port;
    }
}
