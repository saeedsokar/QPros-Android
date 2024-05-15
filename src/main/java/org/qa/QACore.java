package org.qa;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

public class QACore {
    public Properties getConfigsValue(String propertiesFilePath) throws Exception {
        Properties prop = new Properties();
        final ClassLoader cl = Thread.currentThread().getContextClassLoader();
        final InputStream stream = cl.getResourceAsStream(propertiesFilePath);
        prop.load(stream);
        return prop;
    }

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        screenshotName=screenshotName+"_"+ LocalDateTime.now();
        try {
            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(
                    "./src/test/resources/"+ screenshotName + ".png"));
        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
    }
}
