package animal.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHolder {
    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream input = ClassLoader.getSystemResourceAsStream("SQL.Settings.properties");
        properties.load(input);
        return properties;
    }
}
