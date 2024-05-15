package org.qa.utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.Assert;
import org.qa.QACore;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/*
    AbstractAppBaseTest extends from QACore,
    and is handling common appium functions:
    1. Setup and start Appium server
    2. Set Android Driver options, and UIAutomator2 options
    3. stop Appium server
 */
public abstract class AbstractAppiumBase extends QACore {
    String propertiesFilePath = "config-android.properties";
    private static Properties androidProperties = new Properties();
    private static final String APPIUM_IP = "appiumIP";
    private static final String APPIUM_PORT = "appiumPort";
    private static final String APPIUM_TIMEOUT = "appiumTimeout";
    private static final String PLATFORM_NAME = "platformName";
    public static final String PLATFORM_VERSION = "platformVersion";
    public static final String DEVICE_NAME = "deviceName";
    public static final String UDID = "udid";
    public static final String AUTOMATION_NAME = "automationName";
    private static UiAutomator2Options uiAutomator2Options;
    protected static AndroidDriver driver;
    protected static int var;
    protected AppiumDriverLocalService service;

    protected void loadOnSuiteStartup() throws Exception {
        System.out.println("Load Android Configuration on startup");
        androidProperties = getConfigsValue(propertiesFilePath);
        setupAndroidDriver();
    }

    private void setupAndroidDriver() {
        try {
            startAppiumServer();
            setAndroidUiAutomator2Options();
            setAppPackageAndActivity();
            initializeDriver();
            System.out.println("I am driver: " + driver.toString() + " , : " + driver);
        } catch (Exception ex) {
            Assert.fail("could not read properties file: " + this.propertiesFilePath + "\n" + ex.getMessage());
        }
    }

    private void initializeDriver() {
        if (driver == null) {
            driver = new AndroidDriver(getCurrentServerUrl(), uiAutomator2Options);
        }
    }

    private static void setAndroidUiAutomator2Options() throws IOException {
        uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setPlatformName(androidProperties.getProperty(PLATFORM_NAME));
        uiAutomator2Options.setPlatformVersion(androidProperties.getProperty(PLATFORM_VERSION));
        uiAutomator2Options.setDeviceName(androidProperties.getProperty(DEVICE_NAME));
        uiAutomator2Options.autoGrantPermissions();
        uiAutomator2Options.setCapability("newCommandTimeout", androidProperties.getProperty(APPIUM_TIMEOUT));
        uiAutomator2Options.setUdid(androidProperties.getProperty(UDID));
        uiAutomator2Options.setAutomationName(androidProperties.getProperty(AUTOMATION_NAME));
        uiAutomator2Options.setCapability("autoDismissAlerts", true);
        uiAutomator2Options.setCapability("unicodeKeyboard", true);
        uiAutomator2Options.setCapability("resetKeyboard", true);
    }

    private static void setAppPackageAndActivity() {
        System.out.println("setup Package and Activity");
        uiAutomator2Options.setAppPackage(androidProperties.getProperty("appPackage"));
        uiAutomator2Options.setAppActivity(androidProperties.getProperty("appActivity"));
    }

    private void startAppiumServer() {
        if (service != null && service.isRunning()) {
            System.out.println("Appium server is already running");
            return;
        }
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress(androidProperties.getProperty(APPIUM_IP));
//        builder.usingPort(Integer.parseInt(androidProperties.getProperty(APPIUM_PORT)));
        builder.usingAnyFreePort();

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }
        System.out.println("Appium server started");
    }

    protected URL getCurrentServerUrl() {
        return service.getUrl();
    }

    protected void closeAppiumServer() {
        System.out.println("close Appium");
        if (service.isRunning()) {
            driver.quit();
            service.stop();
        }
    }
}
