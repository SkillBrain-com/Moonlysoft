package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Entry point for Cucumber tests via JUnit.
 *
 * Run all tests:
 *   mvn test
 *
 * Filter by tag:
 *   mvn test -Dcucumber.filter.tags=@test
 *   mvn test -Dcucumber.filter.tags="@api and not @notfound"
 *
 * Select environment:
 *   mvn test -Denvironment=local
 *   mvn test -Denvironment=stage -Dcucumber.filter.tags=@smoke
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue     = {"steps", "hooks"},
        plugin   = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber.html"
        },
        // Tags can also be hardcoded here; the -D system property takes precedence at runtime.
        tags = ""
)
public class CucumberTestRunner {
}
