package com.get.appiumtest.android.util;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class AndroidAppium {

    public void doubleTap(String id, AndroidDriver<MobileElement> driver) {

        TouchAction tap = new TouchAction(driver)
                .tap(tapOptions().withElement(element(driver.findElementById("com.get.consumer.app.staging:id/" + id))))
                .waitAction(waitOptions(ofSeconds(1)))
                .tap(tapOptions().withElement(element(driver.findElementById("com.get.consumer.app.staging:id/" + id))));

        tap.perform();
    }

    public void waitAndClickById(String id, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.get.consumer.app.staging:id/" + id))).click();
    }

    public void waitAndClickByXPath(String id, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("com.get.consumer.app.staging:id/" + id))).click();
    }

    public void clickElement(String id, AndroidDriver<MobileElement> driver) {
        driver.findElementById("com.get.consumer.app.staging:id/" + id).click();

    }

    public void clickElementSpecific(String id, AndroidDriver<MobileElement> driver) {
        driver.findElementById(id).click();

    }

    public void fillText(String id, String text, AndroidDriver<MobileElement> driver) {
        // driver.findElementById("com.get.consumer.app.staging:id/" + id).sendKeys(text);
        driver.findElementById("com.get.consumer.app.staging:id/" + id).setValue(text);

    }


    public static boolean isElementPresent(String id, AndroidDriver<MobileElement> driver) {
        return driver.findElementById("com.get.consumer.app.staging:id/" + id).isDisplayed();
    }

    public void scrollDown(int x, int y, AndroidDriver<MobileElement> driver) {
        new TouchAction(driver).press(PointOption.point(550, 640)).waitAction()
                .moveTo(PointOption.point(x, y)).release().perform();

    }

    public void clickAllowPermission(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
    }


    public void verifyCardInfo(String firstName, String lastName, String birthDate, String cardNumber, String laserNumber, AndroidDriver<MobileElement> driver)
    {
        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/firstname_text_view").getText(), firstName);
            System.out.println("[Result]: First name - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: First name - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/lastname_text_view").getText(), lastName);
            System.out.println("[Result]: Last name - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Last name - FAILED");
            throw e;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.YEAR, -7);
        String dob = dateFormat.format(cal.getTime());

        if (birthDate.length() > 0 ) {
            dob = birthDate;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/birthday_text_view").getText(), dob);
            System.out.println("[Result]: Birth date - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Birth date - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/card_number_text_view").getText(), cardNumber);
            System.out.println("[Result]: Card number - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Card number - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/laser_number_text_view").getText(), laserNumber);
            System.out.println("[Result]: Laser number - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Laser name - FAILED");
            throw e;
        }


    }

    public void verifyAddressInfo(String address1, String address2, String province, String city,
                                  String currentAddress1, String currentAddress2, String currentProvince, String currentCity
            ,AndroidDriver<MobileElement> driver) throws InterruptedException {

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/address_line_1_text_view").getText(), address1);
            System.out.println("[Result]: Address line 1 - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Address line 1 - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/address_line_2_text_view").getText(), address2);
            System.out.println("[Result]: Address line 2 - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Address line 2 - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/province_text_view").getText(), province);
            System.out.println("[Result]: Province - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Province - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/city_text_view").getText(), city);
            System.out.println("[Result]: City - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: City - FAILED");
            throw e;
        }

        scrollDown(550,60, driver);
        Thread.sleep(3000);

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/current_address_line_1_text_view").getText(), currentAddress1);
            System.out.println("[Result]: Current address line 1 - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Current address line 1 - FAILED");
            throw e;
        }

        scrollDown(550,60, driver);
        Thread.sleep(3000);

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/current_address_line_2_text_view").getText(), currentAddress2);
            System.out.println("[Result]: Current address line 2 - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Current address line 2 - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/current_province_text_view").getText(), currentProvince);
            System.out.println("[Result]: Current province - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Current province - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/current_city_text_view").getText(), currentCity);
            System.out.println("[Result]: Current city - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Current city - FAILED");
            throw e;
        }
    }

    public void verifyJobinfo(String jobtitle, String jobOthers, String companyName, String province, String city , AndroidDriver<MobileElement> driver){

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/job_text_view").getText()
                    , jobtitle, "[Result]: Job title value is incorrect");
            System.out.println("[Result]: Job title - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Job title - FAILED");
            throw e;
        }

        if (jobtitle == "Others") {
            try {
                Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/job_other_text_view").getText(), jobOthers);
                System.out.println("[Result]: Job others title - PASSED");
            } catch (AssertionError e){
                System.out.println("[Result]: Job others title - FAILED");
                throw e;
            }
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/company_text_view").getText(), companyName);
            System.out.println("[Result]: Company name - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Company name - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/province_text_view").getText(), province);
            System.out.println("[Result]: Company's province - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Company's province - FAILED");
            throw e;
        }

        try {
            Assert.assertEquals(driver.findElementById("com.get.consumer.app.staging:id/city_text_view").getText(), city);
            System.out.println("[Result]: Company's city - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Company's city - FAILED");
            throw e;
        }

    }

    public void clearText(String id, AndroidDriver<MobileElement> driver) {
        // driver.findElementById("com.get.consumer.app.staging:id/" + id).sendKeys(text);
        driver.findElementById("com.get.consumer.app.staging:id/" + id).clear();

    }

    public void fillCardInfo(String firstName, String lastName, String cardNumber, String laserNumber
            ,AndroidDriver<MobileElement> driver,WebDriverWait wait) throws InterruptedException {

        System.out.println("[Start] : Fill in ID card info");
        waitAndClickById("documentIcon", wait);

        if (isElementPresent("firstname_title", driver)) {
            System.out.println("[Validation]: ID card info form displays as expected");
        } else { System.out.println("[Validation]: ID card ifno form does not display as expected");}

        fillText("firstname_text_view", firstName, driver);
        fillText("lastname_text_view", lastName, driver);
        clickElement("birthday_text_view", driver);
        clickElementSpecific("android:id/button1", driver);
        fillText("card_number_text_view", cardNumber, driver);
        scrollDown(330,315, driver);
        fillText("laser_number_text_view", laserNumber, driver);

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in ID card info");

        Thread.sleep(3000);
    }


    public void fillAddressInfoDiff(String address1, String address2, String searchProvince, String province, String searchCity, String city,
                                    String currentAddress1, String currentAddress2, String searchCurrentProvince,
                                    String currentProvince, String searchCurrentCity, String currentCity
            ,AndroidDriver<MobileElement> driver, WebDriverWait wait) {
        System.out.println("[Start] : Fill in Address info");
        waitAndClickById("residence_icon", wait);

        if (isElementPresent("card_id_address_form_title", driver)) {
            System.out.println("[Validation]: Address info form displays as expected");
        } else { System.out.println("[Validation]: Address info form does not display as expected");}


        //address by ID
        fillText("address_line_1_text_view",address1, driver);
        fillText("address_line_2_text_view",address2, driver);
        scrollDown(330,315, driver);
        waitAndClickById("province_text_view", wait);

        if (searchProvince.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchProvince, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + province +"']")).click();

        if (searchCity.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchCity, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + city + "']")).click();


        scrollDown(550,60, driver);

        //current address (search city)
        fillText("current_address_line_1_text_view",currentAddress1, driver);
        scrollDown(550,60, driver);
        waitAndClickById("current_address_line_2_text_view", wait);
        fillText("current_address_line_2_text_view",currentAddress2, driver);
        waitAndClickById("current_province_text_view", wait);
        if (searchCurrentProvince.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchCurrentProvince, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + currentProvince +"']")).click();

        if (searchCurrentCity.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchCurrentCity, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + currentCity + "']")).click();

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in Address info");

    }

    public void fillAddressInfoSame(String address1, String address2, String searchProvince, String province, String searchCity, String city
            ,AndroidDriver<MobileElement> driver, WebDriverWait wait) {

        System.out.println("[Start] : Fill in Address info");
        waitAndClickById("residence_icon", wait);

        if (isElementPresent("card_id_address_form_title", driver)) {
            System.out.println("[Validation]: Address info form displays as expected");
        } else { System.out.println("[Validation]: Address info form does not display as expected");}

        //address by ID
        fillText("address_line_1_text_view",address1, driver);
        fillText("address_line_2_text_view",address2, driver);
        scrollDown(330,315, driver);
        waitAndClickById("province_text_view", wait);

        if (searchProvince.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchProvince, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + province +"']")).click();

        if (searchCity.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchCity, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + city + "']")).click();


        //click same address button
        clickElement("is_same_checkbox", driver);

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in Address info");
    }

    public void fillJobInfoList(String jobtitle, String companyName, String searchProvince, String province, String searchCity, String city
            ,AndroidDriver<MobileElement> driver, WebDriverWait wait) {

        System.out.println("[Start] : Fill in Job info");
        waitAndClickById("job_title_icon", wait);

        if (isElementPresent("job_title", driver)) {
            System.out.println("[Validation]: Job info form displays as expected");
        } else { System.out.println("[Validation]: Job info form does not job_title as expected");}

        clickElement("job_text_view", driver);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + jobtitle +"']")).click();
        fillText("company_text_view",companyName, driver);

        scrollDown( 330,315, driver);
        waitAndClickById("province_text_view", wait);
        if (searchProvince.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchProvince, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + province +"']")).click();

        if (searchCity.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchCity, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + city + "']")).click();

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in Job info");

    }


    public void fillJobInfoOthers(String jobtitle, String companyName, String searchProvince, String province, String searchCity
            ,String city ,AndroidDriver<MobileElement> driver, WebDriverWait wait) {
        System.out.print("[Start] : Fill in Job info");
        waitAndClickById("job_title_icon", wait);

        if (isElementPresent("job_title", driver)) {
            System.out.println("[Validation]: Job info form displays as expected");
        } else { System.out.println("[Validation]: Job info form does not job_title as expected");}

        clickElement("job_text_view", driver);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Others']")).click();
        fillText("job_other_text_view",jobtitle, driver);
        fillText("company_text_view",companyName, driver);

        scrollDown( 330,315, driver);
        waitAndClickById("province_text_view", wait);
        if (searchProvince.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchProvince, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + province +"']")).click();

        if (searchCity.length() > 0 ) {
            clickElement("search_edit_text", driver);
            fillText("search_edit_text",searchCity, driver);
        }
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + city + "']")).click();

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in Job info");

    }

    public void takeIDPhoto(AndroidDriver<MobileElement> driver, WebDriverWait wait){
        System.out.println("[Start] : Take a national ID photo");
        waitAndClickById("take_id_text", wait);
        waitAndClickById("btn_positive", wait);
        clickAllowPermission(wait);
        doubleTap("button_capture", driver);
        waitAndClickById("kyc_confirmation_confirm_btn", wait);
        System.out.println("[Complete] : Take a national ID photo");


    }

    public void takeSelfiePhoto(AndroidDriver<MobileElement> driver, WebDriverWait wait){
        System.out.println("[Start] : Take selfie with ID photo");
        waitAndClickById("take_selfie_text", wait);
        doubleTap("button_capture", driver);
        waitAndClickById("kyc_confirmation_confirm_btn", wait);
        System.out.println("[Complete] : Take selfie with ID photo");


    }


}
