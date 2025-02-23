package TaskManagerDark;

import java.io.*;
import java.util.Properties;

public class Settings {
    private static final String CONFIG_FILE = "config.properties";
    private Properties properties;

    public Settings() {
        properties = new Properties();
        loadSettings();
    }

    public void saveSetting(String key, String value) {
        properties.setProperty(key, value);
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            properties.store(out, "Configurações do TaskManager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSetting(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    private void loadSettings() {
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println("Nenhuma configuração encontrada. Usando padrões.");
        }
    }
}
