package setup;

import exceptions.MobileAppTypeException;
import exceptions.UnknownMobilePlatformException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

import static io.appium.java_client.remote.MobileBrowserType.CHROME;
import static io.appium.java_client.remote.MobileBrowserType.SAFARI;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;


public class Driver extends TestProperties {

    private static AppiumDriver driver;
    private static WebDriverWait wait;
    private DesiredCapabilities capabilities;

    // Properties to be read
    private static String AUT;
    protected static String SUT;
    private static String TEST_PLATFORM;
    private static String DRIVER;
    private static String DEVICE_NAME;
    protected static String UDID;
    protected static String APP_PACKAGE;
    protected static String APP_ACTIVITY;
    private static String CHROMEDRIVER_EXECUTABLE_DIR_KEY;
    private static String CHROMEDRIVER_EXECUTABLE_DIR;
    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws Exception
     */

    protected void prepareDriver() throws Exception {
        if (driver == null) {
            AUT = getProp("aut");
            SUT = getProp("sut");
            TEST_PLATFORM = getProp("platform");
            DRIVER = getProp("driver");
            DEVICE_NAME = getProp("devicename");
            CHROMEDRIVER_EXECUTABLE_DIR_KEY = "chromedriverExecutableDir";
            CHROMEDRIVER_EXECUTABLE_DIR = System.getProperty("user.dir") + getProp(CHROMEDRIVER_EXECUTABLE_DIR_KEY);
            APP_PACKAGE = getProp("appPackage");
            APP_ACTIVITY = getProp("appActivity");
            capabilities = new DesiredCapabilities();
            String browserName;
            //Setup browser depending on platform
            switch (TEST_PLATFORM) {
                case ANDROID:
                    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
                    browserName = CHROME;
                    break;
                case IOS:
                    browserName = SAFARI;
                    break;
                default:
                    throw new UnknownMobilePlatformException();
            }
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);
            capabilities.setCapability(MobileCapabilityType.UDID, UDID);
            //Setup type of application: mobile, web or hybrid
            if (AUT != null && SUT == null) {
                //Native
                File app = new File(AUT);
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
            } else if (SUT != null && AUT == null) {
                //Web
                capabilities.setCapability(CHROMEDRIVER_EXECUTABLE_DIR_KEY, CHROMEDRIVER_EXECUTABLE_DIR);
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            } else throw new MobileAppTypeException();

            // Init driver for local Appium server with capabilities have been set
            driver = new AppiumDriver(new URL(DRIVER), capabilities);
            // Set an object to handle timeouts
            wait = new WebDriverWait(driver, 10);
        } else {
            System.out.println("Driver is already prepared");
        }
    }

    protected AppiumDriver driver() throws Exception {
        if (driver == null) {
            prepareDriver();
        }
        return driver;
    }

    protected WebDriverWait driverWait() throws Exception {
        if (wait == null) {
            prepareDriver();
        }
        return wait;
    }
}

