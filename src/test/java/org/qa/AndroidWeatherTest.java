package org.qa;

import org.qa.pages.HomePage;
import org.qa.pages.PrivacyPolicyPage;
import org.qa.pages.UnitSettingsPage;
import org.qa.utilities.AndroidBaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AndroidWeatherTest extends AndroidBaseTest {

    UnitSettingsPage unitSettingsPage = new UnitSettingsPage();
    PrivacyPolicyPage privacyPolicyPage = new PrivacyPolicyPage();
    HomePage homePage = new HomePage();

    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 0)
    public void openUnitSettingsPage(){
        String selectedTemperature = unitSettingsPage.changeTemperature("C");
        softAssert.assertEquals(selectedTemperature.contains("C"),true);
        String selectedTimeFormat = unitSettingsPage.changeTimeFormat(false);
        softAssert.assertEquals(selectedTimeFormat.contains("24"),true);
        boolean isDoneButtonClicked = unitSettingsPage.clickOnDoneButton();
        softAssert.assertEquals(isDoneButtonClicked,true);
        boolean isProceedToHomeClicked = privacyPolicyPage.clickOnDoneButton();
        softAssert.assertEquals(isProceedToHomeClicked,true);
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void checkHomePageSettings(){
        boolean isC = homePage.checkSelectedTemperature("C");
        softAssert.assertEquals(isC,true);
        boolean hasPM = homePage.checkTimeFormat();
        softAssert.assertEquals(hasPM,false);
        boolean isCorrectRainCount = homePage.isRainAppearing(6);
        softAssert.assertEquals(isCorrectRainCount,true);
        boolean isCorrectHumidityCount = homePage.isHumidityAppearing(6);
        softAssert.assertEquals(isCorrectHumidityCount,true);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void openSettingsMenu(){
        boolean isMenuOpened = homePage.clickOnMenu();
        softAssert.assertEquals(isMenuOpened,true);
        homePage.clickOnUnitSettingsFromMenu();
    }

    @Test(priority = 3)
    public void changeUnitSettingsPage(){
        String selectedTemperature = unitSettingsPage.changeTemperature("F");
        softAssert.assertEquals(selectedTemperature.contains("F"),true);
        String selectedTimeFormat = unitSettingsPage.changeTimeFormat(true);
        softAssert.assertEquals(selectedTimeFormat.contains("12"),true);
        boolean isDoneButtonClicked = unitSettingsPage.clickOnDoneButton();
        softAssert.assertEquals(isDoneButtonClicked,true);
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void checkHomePage(){
        boolean isC = homePage.checkSelectedTemperature("F");
        softAssert.assertEquals(isC,true);
        boolean hasPM = homePage.checkTimeFormat();
        softAssert.assertEquals(hasPM,true);
        boolean isCorrectRainCount = homePage.isRainAppearing(5);
        softAssert.assertEquals(isCorrectRainCount,true);
        boolean isCorrectHumidityCount = homePage.isHumidityAppearing(5);
        softAssert.assertEquals(isCorrectHumidityCount,true);
        softAssert.assertAll();
    }
}
