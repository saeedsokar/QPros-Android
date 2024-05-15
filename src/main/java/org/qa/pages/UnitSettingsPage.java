package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UnitSettingsPage extends AbstractPage{
    private final By temperatureDropdownLocator = By.id("com.info.weather.forecast:id/iv_temp_dropdown");
    private final By cTemperatureLocator = By.xpath("//android.widget.TextView[@text=\"C\"]");
    private final By fTemperatureLocator = By.xpath("//android.widget.TextView[@text=\"F\"]");
    private final By selectedTemperatureLocator = By.id("com.info.weather.forecast:id/tv_temp_setting");

    private final By timeFormatDropDownLocator = By.id("com.info.weather.forecast:id/iv_timeformat_dropdown");
    private final By twentyFourHourLocator = By.xpath("//android.widget.TextView[@text=\"24 Hour\"]");
    private final By twelveHourLocator = By.xpath("//android.widget.TextView[@text=\"12 Hour\"]");
    private final By timeFormatSelectedLocator = By.id("com.info.weather.forecast:id/tv_timeformat_setting");

    private final By doneButtonLocator = By.id("com.info.weather.forecast:id/tv_button_done");

    public String changeTemperature(String temp){
        try{
            clickOn(temperatureDropdownLocator);
            if(temp.contains("C")){
                clickOn(cTemperatureLocator);
            }else{
                clickOn(fTemperatureLocator);
            }
            WebElement element = findElementByLocatorWithVisibility(selectedTemperatureLocator);
            logger.log(LogStatus.INFO,"Temp: " + element.getText());
            return element.getText();
        }catch(Exception ex){
            logger.log(LogStatus.ERROR, "failed to change temperature to C: " + ex.getMessage());
            return "";
        }
    }

    public String changeTimeFormat(boolean hasMidday){
        try{
            clickOn(timeFormatDropDownLocator);
            if(hasMidday){
                clickOn(twelveHourLocator);
            }else{
                clickOn(twentyFourHourLocator);
            }
            WebElement element = findElementByLocatorWithVisibility(timeFormatSelectedLocator);
            logger.log(LogStatus.INFO,"Temp: " + element.getText());
            return element.getText();
        }catch(Exception ex){
            logger.log(LogStatus.ERROR, "failed to change time format to 24H : " + ex.getMessage());
            return "";
        }
    }
    public boolean clickOnDoneButton() {
        try {
            clickOn(doneButtonLocator);
            logger.log(LogStatus.INFO, "proceed to next Screen");
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to click on done button due to: " + ex.getMessage());
            return false;
        }
    }

}
