package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends AbstractPage {
    private final By allowAccessLocation = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private final By currentLocationTitle = By.id("com.info.weather.forecast:id/tv_title");
    private final By currentTemperatureUnit = By.id("com.info.weather.forecast:id/tv_current_temper_unit");
    private final By currentTemperatureValue = By.id("com.info.weather.forecast:id/iostv_temperature");
    private final By windSpeedLocator = By.id("com.info.weather.forecast:id/tv_wind_speed");
    private final By timeCheck = By.id("com.info.weather.forecast:id/tv_date");
    private final By rainLocator = By.xpath("//android.widget.TextView[@resource-id=\"com.info.weather.forecast:id/tv_rain_chance\"]");
    private final By humidityLocator = By.xpath("//android.widget.TextView[@resource-id=\"com.info.weather.forecast:id/tv_humidity\"]");
    private final By menuLocator = By.id("com.info.weather.forecast:id/iv_bt_navigation_setting");
    private final By menuUnitSetting = By.id("com.info.weather.forecast:id/ll_item_unit_setting");

    public boolean checkSelectedTemperature(String unit) {
        try {
            WebElement element = findElementByLocatorWithVisibility(currentTemperatureUnit);
            logger.log(LogStatus.INFO, "current Temperature unit is: " + element.getText());
            if (element.getText().contains(unit)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to locate temperature unit due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean checkTimeFormat() {
        try {
            WebElement element = findElementByLocatorWithVisibility(timeCheck);
            logger.log(LogStatus.INFO, "current Time Format unit is: " + element.getText());
            return element.getText().contains("pm") || element.getText().contains("am");
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to locate temperature unit due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean isRainAppearing(int count) {
        try {
            List<WebElement> rainElements = findElementList(rainLocator);
            logger.log(LogStatus.INFO, "Rain List size: " + rainElements.size());
            if(!rainElements.isEmpty()) {
                for(WebElement element:rainElements) {
                    if(element.isDisplayed()) {
                        logger.log(LogStatus.INFO, "rain unit is: " + element.getText() + " & is displayed: " + element.isDisplayed());
                        count--;
                        if (count == 0) return true;
                    }else {
                        return false;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to locate rain units due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean isHumidityAppearing(int count) {
        try {
            List<WebElement> humidityElements = findElementList(humidityLocator);
            if(!humidityElements.isEmpty()) {
                for(WebElement element:humidityElements) {
                    if(element.isDisplayed()) {
                        logger.log(LogStatus.INFO, "Humidity unit is: " + element.getText() + " & is displayed: " + element.isDisplayed());
                        count--;
                        if (count < 0) {return true;}
                    }else {
                        return false;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to locate humidity units due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean clickOnAccessLocationButton() {
        try {
            clickOn(allowAccessLocation);
            logger.log(LogStatus.INFO, "allow Access Location");
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to click on allowAccessLocation button due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean clickOnMenu() {
        try {
            clickOn(menuLocator);
            logger.log(LogStatus.INFO, "open menu");
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to click on menu button due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean clickOnUnitSettingsFromMenu() {
        try {
            clickOn(menuUnitSetting);
            logger.log(LogStatus.INFO, "open Unit settings from menu");
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to click on unit settings button due to: " + ex.getMessage());
            return false;
        }
    }
}
