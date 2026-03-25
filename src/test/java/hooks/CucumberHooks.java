package hooks;

import config.EnvironmentConfig;
import io.cucumber.java.*;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileUtils;
import utils.LogUtils;

import java.io.IOException;

/**
 * Global Cucumber hooks — run before/after every scenario and the full suite.
 * <p>
 * Serenity handles screenshots and report attachment automatically;
 * these hooks are for additional cross-cutting concerns (logging, context setup, etc.).
 */
public class CucumberHooks {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberHooks.class);

    @BeforeAll
    public static void beforeSuite() throws IOException {
        LOG.info("=== Test Suite Starting ===");
        LOG.info("Active environment : {}", EnvironmentConfig.getActiveEnvironment());
        LOG.info("Base URL           : {}", EnvironmentConfig.getBaseUrl());
        LOG.info("API URL            : {}", EnvironmentConfig.getApiUrl());
        LOG.info("BROWSER            : {}", EnvironmentConfig.getBrowser());
        LOG.info("CLEARING previous test report generated in report folder.");
        FileUtils.clearReportFolder();
        LOG.info("CLEARING previous test logs...");
        LogUtils.clearLogFile();
    }


    @Before
    public void beforeScenario(Scenario scenario) {
        LOG.info("--- Starting: [{}] {}", scenario.getId(), scenario.getName());
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        LOG.info("=== Finished scenario: [{}] - Status: {} ===",
                scenario.getName(), scenario.getStatus());

        if (scenario.isFailed()) {
            captureScreenshot(scenario);
        }
        // Serenity manages the WebDriver lifecycle — no manual quit needed here
    }

    private void captureScreenshot(Scenario scenario) {
        try {
            WebDriver driver = ThucydidesWebDriverSupport.getDriver();
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
                LOG.info("Screenshot captured for failed scenario: {}", scenario.getName());
            }
        } catch (Exception e) {
            LOG.warn("Could not capture screenshot: {}", e.getMessage());
        }
    }

    @AfterAll
    public static void afterSuite() {
        LOG.info("=== Test Suite Finished ===");
    }
}
