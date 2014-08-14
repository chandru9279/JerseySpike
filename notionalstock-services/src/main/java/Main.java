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

        loadSettings();
        setupConnector(server);
        configureContext(server);

        server.start();
        server.join();
    }

    private static void setupConnector(Server server) {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(Integer.parseInt(System.getProperty("port")));
        server.addConnector(connector);
    }

    private static void configureContext(Server server) {

        WebAppContext webAppContext = new WebAppContext();

        webAppContext.setContextPath("/");

        ProtectionDomain domain = Main.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        webAppContext.setWar(location.toExternalForm());

        server.setHandler(webAppContext);
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