package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;

public class UtilsPage extends PageObject {


    public JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

}
