package config;

import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Central accessor for environment-specific configuration values.
 *
 * Values are read from src/test/resources/serenity.conf under the
 * block matching the active environment (default: "test").
 *
 * Select the environment at runtime:
 *   mvn test -Denvironment=local
 *   mvn test -Denvironment=test
 *   mvn test -Denvironment=stage
 */
public class EnvironmentConfig {

    private static final Logger LOG = LoggerFactory.getLogger(EnvironmentConfig.class);

    private static final EnvironmentVariables ENV_VARS =
            SystemEnvironmentVariables.createEnvironmentVariables();

    private EnvironmentConfig() {
        // Utility class — no instances
    }

    /** Active environment name (local | test | stage). */
    public static String getActiveEnvironment() {
        return System.getProperty("environment", "test");
    }

    /** Base URL for the web application under test. */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    /** Base URL for the API under test. */
    public static String getApiUrl() {
        return getProperty("api.url");
    }

    /** Browser to use (chrome | firefox | edge). */
    public static String getBrowser() {
        return getProperty("browser");
    }

    /**
     * Generic accessor for any key defined in the active environment block
     * of serenity.conf.
     *
     * @param key property key (e.g. "base.url")
     * @return resolved value, or {@code null} when not found
     */
//    TODO - leave it if you want to read the test properties from the serenity.conf file
//    public static String getProperty(String key) {
//        String value = EnvironmentSpecificConfiguration
//                .from(ENV_VARS)
//                .getProperty(key);
//        LOG.debug("[env={}] {} = {}", getActiveEnvironment(), key, value);
//        return value;
//    }

    public static String getProperty(String key) {
        Properties properties = new Properties();
        String env = System.getProperty("environment", "test"); // local | test | stage
        InputStream is = EnvironmentConfig.class
                .getClassLoader()
                .getResourceAsStream("environments/" + env + ".properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return (value != null && !value.isBlank()) ? value : defaultValue;
    }
}
