package scenarios.nativeTests;

import enums.PropertyFiles;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.nativeApps.ContactPage;
import pageObjects.nativeApps.ContactManagerPage;
import scenarios.Hooks;

import static enums.Constants.*;
import static enums.ContactTypes.*;
import static enums.Contacts.IVAN;

@Test(groups = {"native"})
public class NativeTest extends Hooks {

    private ContactManagerPage contactManagerPage;
    private ContactPage contactPage;

    @BeforeMethod
    public void preparePageObjects() throws Exception {
        contactManagerPage = new ContactManagerPage(driver());
        contactPage = new ContactPage(driver());
    }

    //Sending proper Property File depending on the application type: NATIVE, WEB
    protected NativeTest() {
        super(PropertyFiles.NATIVE);
    }

    @Test(description = "Simple elements and functionality test of Contact Manager app")
    public void contactManagerTest() {
        //1 check start page title in app
        contactManagerPage.checkTitle(CONTACT_MANAGER_PAGE_TITLE);

        //2 click 'Add Contact' button
        contactManagerPage.addContact();

        //3 check title in opened page
        contactPage.checkTitle(ADD_CONTACT_PAGE_TITLE);

        //4 remove Keyboard
        contactPage.removeKeyboard();

        //5 check 'Save Button' is displayed
        contactPage.checkButtonExist(SAVE_BUTTON);

        //6 create new contact
        contactPage.createNewContact(IVAN, OTHER, MOBILE);

        //7 check whether all fields are filled
        contactPage.checkContactData(IVAN, OTHER, MOBILE);

        //8 submit new contact
        contactPage.save();
    }
}
