package pageObjects.web;

import enums.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import setup.Driver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPage extends Driver {

    private AppiumDriver driver;

    @FindBy(xpath = "//div[@id='intro']/p")
    private WebElement headerText;

    @FindBy(xpath = "//div[@id='home-panel-domains']//a[@href='/domains']")
    private WebElement domainName;

    @FindBy(xpath = "//div[@id='home-news']")
    private WebElement privacyPolicy;

    public MainPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void openSite(Constants value) throws Exception {
        driver.get(value.toString());
        driverWait().until(ExpectedConditions.urlToBe(SUT));
    }

    public void checkTitle(Constants title) {
        assertEquals(driver.getTitle(), title.toString());
    }

    public void checkDomainName(Constants domainName) {
        assertEquals(this.domainName.getText(), domainName.toString());
    }

    public void checkHeaderText(Constants headerText) {
        assertEquals(this.headerText.getText(), headerText.toString());
    }

    public void checkPrivacyPolicyExists() {
        assertTrue(privacyPolicy.isDisplayed());
    }
}
