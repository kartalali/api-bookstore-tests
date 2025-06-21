package config;

public class ConfigManager {
    private ConfigManager() {}

    private static final String ENV_BASE_URL = System.getProperty("base.url");

    public static String baseUrl() {
        return ENV_BASE_URL != null
                ? ENV_BASE_URL
                : "https://fakerestapi.azurewebsites.net";
    }
}
