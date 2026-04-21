package pages;

import config.EnvironmentConfig;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    public class AdminPage extends PageObject {

        private static final Logger LOG = LoggerFactory.getLogger(AdminPage.class);
        public void navigateToAdminPage() {
            LOG.info("Navigating directly to admin page...");
            String url = EnvironmentConfig.getBaseUrl() + "/admin";
            openAt(url);
        }

        public void verifyAccessIsDenied() {
            LOG.info("Verifying access is denied for regular user...");
            String currentUrl = getDriver().getCurrentUrl();
            Assert.assertFalse(
                    "BUG: Regular user should NOT access /admin but is on: " + currentUrl,
                    currentUrl.contains("/admin")
            );

        }
}
