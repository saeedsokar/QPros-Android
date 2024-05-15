package org.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.utilities.AndroidBaseTest;

import java.time.Duration;
import java.util.List;

public class AbstractPage extends AndroidBaseTest {
    WebDriverWait wait;
    private final int duration = 15;


    protected WebElement waitForVisibilityOf(By by) throws Exception {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitForPresenceOf(By by) throws Exception {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebElement findElementByLocatorWithVisibility(By locator) throws Exception {
        WebElement element = null;
        try {
            element = waitForVisibilityOf(locator);
            return element;
        } catch (Exception e) {
            throw new Exception("NoSuchElement : can't locate the element with specified locators" + e.getMessage());
        }
    }

    protected List<WebElement> findElementList(By by) throws Exception {
        return driver.findElements(by);
    }

    protected List<WebElement> findElementList(WebElement element, By by) throws Exception {
        return element.findElements(by);
    }

    public void typeText(By by, final String inputText) throws Exception {
        WebElement textElement = waitForPresenceOf(by);
        textElement.clear();
        textElement.sendKeys(inputText);
    }

    public void clickOn(By by) throws Exception {
        waitForVisibilityOf(by).click();
    }

}
