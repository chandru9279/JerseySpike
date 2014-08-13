package com.morrisons.notionalstock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("ping")
public class Ping {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "ACK";
    }
}
