package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;

public class PrivacyPolicyPage extends AbstractPage{
    private final By proceedLocator = By.id("com.info.weather.forecast:id/ll_got_it");
    private final By privacyScreenTitleLocator = By.xpath("//android.widget.TextView[@text=\"Privacy policy\"]");

    public boolean clickOnDoneButton() {
        try {
            clickOn(proceedLocator);
            logger.log(LogStatus.INFO, "proceed to Home screen");
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to click on GOT it button due to: " + ex.getMessage());
            return false;
        }
    }

}
