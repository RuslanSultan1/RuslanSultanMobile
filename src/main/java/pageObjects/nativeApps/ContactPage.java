package pageObjects.nativeApps;

import enums.Constants;
import enums.ContactTypes;
import enums.Contacts;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactPage {

    private AppiumDriver driver;

    public ContactPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id = "title")
    private RemoteWebElement pageTitle;

    @FindBy(id = "contactSaveButton")
    private RemoteWebElement saveButton;

    @FindBy(id = "contactNameEditText")
    private RemoteWebElement contactNameField;

    @FindBy(id = "contactPhoneEditText")
    private RemoteWebElement contactPhoneField;

    @FindBy(xpath = "//android.widget.TableRow[6]//android.widget.TextView")
    private RemoteWebElement contactPhoneTypeField;

    @FindBy(xpath = "//android.widget.ListView/*")
    private List<RemoteWebElement> types;

    @FindBy(id = "contactEmailEditText")
    private RemoteWebElement contactEmailField;

    @FindBy(xpath = "//android.widget.TableRow[8]//android.widget.TextView")
    private RemoteWebElement contactEmailTypeField;

    public void checkButtonExist(Constants button) {
        assertTrue(saveButton.isDisplayed());
        assertEquals(saveButton.getText(), button.toString());
    }

    public void checkTitle(Constants title) {
        assertEquals(this.pageTitle.getText(), title.toString());
    }

    public void removeKeyboard() {
        driver.hideKeyboard();
    }

    public void save() {
        saveButton.click();
    }

    public void createNewContact(Contacts contact, ContactTypes phoneType, ContactTypes emailType) {
        contactNameField.click();
        contactNameField.sendKeys(contact.getContactName());
        fillPhone(contact, phoneType);
        fillEmail(contact, emailType);
    }

    public void checkContactData(Contacts contact, ContactTypes phoneType, ContactTypes emailType) {
        assertEquals(contactNameField.getText(), contact.getContactName());
        assertEquals(contactPhoneField.getText(), contact.getContactPhone());
        assertEquals(contactEmailField.getText(), contact.getContactEmail());
        assertEquals(contactPhoneTypeField.getText(), phoneType.toString());
        assertEquals(contactEmailTypeField.getText(), emailType.toString());
    }

    private void fillPhone(Contacts contact, ContactTypes phoneType) {
        contactPhoneField.click();
        contactPhoneField.sendKeys(contact.getContactPhone());
        contactPhoneTypeField.click();
        for (RemoteWebElement type : types) {
            if (type.getText().equals(phoneType.toString())) {
                type.click();
                break;
            }
        }
        removeKeyboard();
    }

    private void fillEmail(Contacts user, ContactTypes emailType) {
        contactEmailField.click();
        contactEmailField.sendKeys(user.getContactEmail());
        contactEmailTypeField.click();
        for (RemoteWebElement type : types) {
            if (type.getText().equals(emailType.toString())) {
                type.click();
                break;
            }
        }
        removeKeyboard();
    }
}