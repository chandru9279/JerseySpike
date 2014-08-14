package com.morrisons.notionalstock;

import com.google.gson.stream.JsonWriter;
import jersey.repackaged.com.google.common.base.Stopwatch;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Path("openingstock")
public class OpeningStock {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() throws IOException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String connectionString = System.getProperty("connectionString");
        String query = System.getProperty("query");

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = null;
        Stopwatch watch = new Stopwatch();
        watch.start();
        try {
            con = DriverManager.getConnection(connectionString);
            Statement statement = con.createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            long elapsedDatabase = watch.elapsed(TimeUnit.MILLISECONDS);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = new JsonWriter(stringWriter);
            while(resultSet.next()) {
                writer.beginObject();
                // loop rs.getResultSetMetadata columns 760725
                for(int idx=1; idx<=rsmd.getColumnCount(); idx++) {
                    String columnLabel = rsmd.getColumnLabel(idx);
                    writer.name(columnLabel.isEmpty() ? "count" : columnLabel); // write key:value pairs
                    writer.value(resultSet.getString(idx));
                }
                writer.name("DB Time in milliseconds");
                writer.value(elapsedDatabase);
                writer.endObject();
            }
            writer.close();
            statement.close();
            return stringWriter.toString();
        }
        finally {
            if(con != null)
                con.close();
        }
    }
}
