package com.morrisons.notionalstock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Path("openingstock")
public class OpeningStock {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() throws IOException {
        return System.getProperty("connectionString");
    }
}
