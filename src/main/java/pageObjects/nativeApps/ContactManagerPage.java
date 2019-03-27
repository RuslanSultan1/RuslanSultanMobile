package pageObjects.nativeApps;

import enums.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactManagerPage {

    private AppiumDriver driver;

    public ContactManagerPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id = "addContactButton")
    private AndroidElement addContactButton;

    @FindBy(id = "title")
    private RemoteWebElement pageTitle;

    public void addContact() {
        assertTrue(addContactButton.isDisplayed());
        addContactButton.click();
    }

    public void checkTitle(Constants title) {
        assertEquals(this.pageTitle.getText(), title.toString());
    }
}
