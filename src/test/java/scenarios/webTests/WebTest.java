package scenarios.webTests;

import enums.PropertyFiles;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.web.MainPage;
import scenarios.Hooks;

import static enums.Constants.*;

@Test(groups = {"web"})
public class WebTest extends Hooks {

    private MainPage mainPage;
    private AppiumDriver driver;

    @BeforeMethod
    public void preparePageObjects() throws Exception {
        mainPage = new MainPage(driver());
    }

    //Sending proper Property File depending on the application type: NATIVE, WEB
    protected WebTest() {
        super(PropertyFiles.WEB);
    }

    @Test(description = "Simple test checking elements of 'iana.com' site")
    public void webTest() throws Exception {
        //1 open
        mainPage.openSite(SITE_URL);

        //2 check main page title
        mainPage.checkTitle(MAIN_PAGE_TITLE);

        //3 check header text
        mainPage.checkHeaderText(HEADER_TEXT);

        //4 check 'Domains Name' text
        mainPage.checkDomainName(DOMAIN_NAME);

        //5 check that there is Privacy Policy section
        mainPage.checkPrivacyPolicyExists();
    }
}
