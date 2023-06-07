import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;

public class ConfigReader {

    private static final String CONFIG_FILE = "env.yaml";
    private static HashMap<String, String> configMap;

    public static void load() {
        System.out.println("File to read is " + CONFIG_FILE);
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        Yaml yaml = new Yaml();
        configMap = yaml.load(inputStream);
    }

    public static String getProperty(String property) {
        return configMap.get(property);
    }
}
