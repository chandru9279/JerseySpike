import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.ProtectionDomain;
import java.util.Properties;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(10000);
        server.addConnector(connector);

        loadSettings();

        configureContext(server);

        server.start();
        server.join();
    }

    private static void configureContext(Server server) {
        ProtectionDomain domain = Main.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        WebAppContext webapp = new WebAppContext();

        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());

        server.setHandler(webapp);
    }

    private static void loadSettings() throws IOException {
        String pathToSettings = System.getProperty("settings");
        System.out.println("Path to settings: " + pathToSettings);
        final Properties fromFile = new Properties();
        fromFile.load(new FileInputStream(new File(pathToSettings)));
        for (String name : fromFile.stringPropertyNames()) {
            String value = fromFile.getProperty(name);
            System.setProperty(name, value);
        }
    }
}