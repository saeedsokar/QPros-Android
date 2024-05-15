package org.qa.utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class AndroidBaseTest extends AbstractAppiumBase {
    public static ExtentReports extentReports;
    public static ExtentTest logger;

    private void reportHandler() {
        System.out.println(LocalDateTime.now());
        extentReports = new ExtentReports("reports/index/report " + LocalDateTime.now() + ".html");
        extentReports.addSystemInfo("Automation", "Quality Professionals Assignment");
        extentReports.addSystemInfo("Author", "Saeed Sokar");
        extentReports.addSystemInfo("version", "1");
    }

    @BeforeSuite(alwaysRun = true)
    public void configureAppium(){
        try {
            System.out.println("Configure Appium in Before suite");
            loadOnSuiteStartup();
            reportHandler();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("TearDown Appium");
        extentReports.flush();
        closeAppiumServer();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger = extentReports.startTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult result) throws InterruptedException {

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test Failed due to: " + result.getThrowable());
            logger.log(LogStatus.FAIL, logger.getDescription());
        } else {
            logger.log(LogStatus.SKIP, "Test escape as there is no error/exception ");
        }
        captureScreenshot(driver, result.getName());
    }
}
