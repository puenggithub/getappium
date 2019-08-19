package com.get.appiumtest.android;

import com.get.appiumtest.android.util.AndroidAppium;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class kycTest extends AndroidAppium {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;


    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("skipUnlock","true");
        //caps.setCapability("appPackage", "com.example.myapplication");
        //caps.setCapability("appActivity","com.example.myapplication.LoginActivity");
        caps.setCapability("appPackage", "com.get.consumer.app.staging");
        caps.setCapability("appActivity","com.gojek.gopay.v2.home.launcher.GoPayLauncherActivity");
        caps.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 20);
    }


    @Test
    public void Testcase01_BasicCase() throws InterruptedException {

        //############## Click to launch KYC process ##############//
        System.out.println("//##########################################################//");
        System.out.println("// Testcase: Testcase01_BasicCase");
        System.out.println("// Objective: To verify if user can fill in all forms");
        System.out.println("//##########################################################//");
        waitAndClickById("goPayLauncherPagerImage", wait);
        waitAndClickById("upgrade_now", wait);


        //############## Take photo ##############//
        takeIDPhoto(driver, wait);
        takeSelfiePhoto(driver, wait);

        //############## Type in all info ##############//
        fillCardInfo("Hello","Monday", "3876014697907", "AA1234567890", driver, wait);
        fillAddressInfoDiff("Chartered Square Building", "Sathorn", "", "Bangkok", ""
                , "Bang Bon", "19th Floor Central World", "Central World", ""
                , "Bangkok", "pa", "Pathum Wan", driver, wait);
        fillJobInfoList("Student", "Test Academy", "", "Bangkok", "", "Bang Bon", driver, wait);

        //############## Verify all inputs ##############//
        System.out.println("***********************");
        System.out.println("*    [VALIDATION]     *");
        System.out.println("***********************");


        //verify ID card info
        waitAndClickById("documentIcon", wait);
        verifyCardInfo("Hello", "Monday", "", "3876014697907", "AA1234567890", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify address info
        waitAndClickById("residence_icon", wait);
        verifyAddressInfo("Chartered Square Building", "Sathorn", "Bangkok", "Bang Bon",
                "19th Floor Central World", "Central World", "Bangkok", "Pathum Wan", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify selected job info
        waitAndClickById("job_title_icon", wait);
        verifyJobinfo("Student", "", "Test Academy", "Bangkok", "Bang Bon", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        if (driver.findElementById("com.get.consumer.app.staging:id/confirm_button").isEnabled()) {
            System.out.println("[Validation]: Submit button available - PASSED");
        } else {
            System.out.println("[Validation]: Submit button available - FAILED");
        }

        Assert.assertTrue(driver.findElementById("com.get.consumer.app.staging:id/confirm_button").isEnabled());
        clickElement("confirm_button", driver);
        System.out.println("[Complete] : All validation");


    }
    //same address and others job title
    @Test
    public void Testcase02_SameAddressAndOthersJob() throws InterruptedException {

        //############## Click to launch KYC process ##############//
        System.out.println("//##########################################################//");
        System.out.println("// Testcase: Testcase02_SameAddressAndOthersJob");
        System.out.println("// Objective: To verify if user can fill in all forms with select same address and others job");
        System.out.println("//##########################################################//");
        waitAndClickById("goPayLauncherPagerImage", wait);
        waitAndClickById("upgrade_now", wait);

        //############## Take photo ##############//
        takeIDPhoto(driver, wait);
        takeSelfiePhoto(driver, wait);

        //############## Type in all info ##############//
        fillCardInfo("Hello","Monday", "3876014697907", "AA1234567890", driver, wait);
        fillAddressInfoSame("Chartered Square Building", "Sathorn", "", "Bangkok", ""
                , "Bang Bon", driver, wait);
        fillJobInfoOthers("Test", "Test Academy", "", "Bangkok", "", "Bang Bon", driver, wait);


        //############## Verify all inputs ##############//
        System.out.println("***********************");
        System.out.println("*    [VALIDATION]     *");
        System.out.println("***********************");

        //verify ID card info
        waitAndClickById("documentIcon", wait);
        verifyCardInfo("Hello", "Monday", "", "3876014697907", "AA1234567890", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify address info
        waitAndClickById("residence_icon", wait);
        verifyAddressInfo("Chartered Square Building", "Sathorn", "Bangkok", "Bang Bon",
                "Chartered Square Building", "Sathorn", "Bangkok", "Bang Bon", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify selected job info
        waitAndClickById("job_title_icon", wait);
        verifyJobinfo("Others", "Test", "Test Academy", "Bangkok", "Bang Bon", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        if (driver.findElementById("com.get.consumer.app.staging:id/confirm_button").isEnabled()) {
            System.out.println("[Validation]: Submit button available - PASSED");
        } else {
            System.out.println("[Validation]: Submit button available - FAILED");
        }

        Assert.assertTrue(driver.findElementById("com.get.consumer.app.staging:id/confirm_button").isEnabled());
        clickElement("confirm_button", driver);
        System.out.println("[Complete] : All validation");

    }

    @Test
    public void Testcase03_EditData() throws InterruptedException {
        //############## Click to launch KYC process ##############//
        System.out.println("//##########################################################//");
        System.out.println("// Testcase: Testcase03_EditData");
        System.out.println("// Objective: To verify if user can fill in all forms with select same address and others job");
        System.out.println("//##########################################################//");
        waitAndClickById("goPayLauncherPagerImage", wait);
        waitAndClickById("upgrade_now", wait);

        //############## Take photo ##############//
        takeIDPhoto(driver, wait);
        takeSelfiePhoto(driver, wait);


        //############## Type in all info ##############//
        fillCardInfo("Hello","Monday", "3876014697907", "AA1234567890", driver, wait);
        fillAddressInfoDiff("Chartered Square Building", "Sathorn", "", "Bangkok", ""
                , "Bang Bon", "19th Floor Central World", "Central World", ""
                , "Bangkok", "pa", "Pathum Wan", driver, wait);
        fillJobInfoList("Student", "Test Academy", "", "Bangkok", "", "Bang Bon", driver, wait);


        //############## Verify all inputs ##############//
        System.out.println("***********************************");
        System.out.println("*    [VALIDATION BEFORE EDIT]     *");
        System.out.println("***********************************");

        //verify ID card info
        waitAndClickById("documentIcon", wait);
        verifyCardInfo("Hello", "Monday", "","3876014697907", "AA1234567890", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify address info
        waitAndClickById("residence_icon", wait);
        verifyAddressInfo("Chartered Square Building", "Sathorn", "Bangkok", "Bang Bon",
                "19th Floor Central World", "Central World", "Bangkok", "Pathum Wan", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify selected job info
        waitAndClickById("job_title_icon", wait);
        verifyJobinfo("Student", "", "Test Academy", "Bangkok", "Bang Bon", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        System.out.println("***********************************");
        System.out.println("*        [Start Edit Data]        *");
        System.out.println("***********************************");

        //############## Edit Card Info ##############//
        System.out.println("[Start] : Fill in ID card info");
        waitAndClickById("documentIcon", wait);

        if (isElementPresent("firstname_title", driver)) {
            System.out.println("[Validation]: ID card info form displays as expected");
        } else { System.out.println("[Validation]: ID card ifno form does not display as expected");}

        clearText("firstname_text_view", driver);
        fillText("firstname_text_view", "Sawasdee", driver);

        clearText("lastname_text_view", driver);
        fillText("lastname_text_view", "Tuesday", driver);

        clearText("card_number_text_view", driver);
        fillText("card_number_text_view", "4041667109785", driver);

        scrollDown(330,315, driver);
        clearText("laser_number_text_view", driver);
        fillText("laser_number_text_view", "BB1234567890", driver);

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in ID card info");

        //############## Edit Address Info ##############//
        System.out.println("[Start] : Fill in Address info");
        waitAndClickById("residence_icon", wait);

        if (isElementPresent("card_id_address_form_title", driver)) {
            System.out.println("[Validation]: Address info form displays as expected");
        } else { System.out.println("[Validation]: Address info form does not display as expected");}


        //address by ID
        clearText("address_line_1_text_view", driver);
        fillText("address_line_1_text_view","Mahanakorn", driver);

        clearText("address_line_2_text_view", driver);
        fillText("address_line_2_text_view","Silom", driver);
        scrollDown(330,315, driver);
        waitAndClickById("province_text_view", wait);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bangkok']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bang Na']")).click();


        scrollDown(550,60, driver);

        //current address (search city)
        clearText("current_address_line_1_text_view", driver);
        fillText("current_address_line_1_text_view","Empire Building", driver);
        scrollDown(550,60, driver);

        clearText("current_address_line_2_text_view", driver);
        waitAndClickById("current_address_line_2_text_view", wait);
        fillText("current_address_line_2_text_view","Sathorn", driver);
        waitAndClickById("current_province_text_view", wait);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bangkok']")).click();
        clickElement("search_edit_text", driver);
        fillText("search_edit_text", "Bang Rak", driver);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bang Rak']")).click();

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in Address info");

        //############## Edit Job Info ##############//
        System.out.println("[Start] : Fill in Job info");
        waitAndClickById("job_title_icon", wait);

        if (isElementPresent("job_title", driver)) {
            System.out.println("[Validation]: Job info form displays as expected");
        } else { System.out.println("[Validation]: Job info form does not job_title as expected");}

        clickElement("job_text_view", driver);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Freelance']")).click();

        clearText("company_text_view", driver);
        fillText("company_text_view","Anywhere", driver);

        scrollDown( 330,315, driver);
        waitAndClickById("province_text_view", wait);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bangkok']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bang Kapi']")).click();

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in Job info");

        //############## Verify all inputs ##############//
        System.out.println("***********************************");
        System.out.println("*    [VALIDATION BEFORE EDIT]     *");
        System.out.println("***********************************");

        //verify ID card info
        waitAndClickById("documentIcon", wait);
        verifyCardInfo("Sawasdee", "Tuesday", "", "4041667109785", "BB1234567890", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify address info
        waitAndClickById("residence_icon", wait);
        verifyAddressInfo("Mahanakorn", "Silom", "Bangkok", "Bang Na",
                "Empire Building", "Sathorn", "Bangkok", "Bang Rak", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify selected job info
        waitAndClickById("job_title_icon", wait);
        verifyJobinfo("Freelance", "", "Anywhere", "Bangkok", "Bang Kapi", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        if (driver.findElementById("com.get.consumer.app.staging:id/confirm_button").isEnabled()) {
            System.out.println("[Validation]: Submit button available - PASSED");
        } else {
            System.out.println("[Validation]: Submit button available - FAILED");
        }

        Assert.assertTrue(driver.findElementById("com.get.consumer.app.staging:id/confirm_button").isEnabled());
        clickElement("confirm_button", driver);
        System.out.println("[Complete] : All validation");


    }

    //Fill and delete
    @Test
    public void Testcase04_FilledAndDelete () throws InterruptedException {

        //############## Click to launch KYC process ##############//
        System.out.println("//##########################################################//");
        System.out.println("// Testcase: Testcase04_FilledAndDelete");
        System.out.println("// Objective: To verify if user click delete button after complete filling in the forms all data will be deleted");
        System.out.println("//##########################################################//");
        waitAndClickById("goPayLauncherPagerImage", wait);
        waitAndClickById("upgrade_now", wait);

        //############## Take photo ##############//
        takeIDPhoto(driver, wait);
        takeSelfiePhoto(driver, wait);


        //############## Type in all info ##############//
        fillCardInfo("Hello","Monday", "3876014697907", "AA1234567890", driver, wait);
        fillAddressInfoSame("Chartered Square Building", "Sathorn", "", "Bangkok", ""
                , "Bang Bon", driver, wait);
        fillJobInfoOthers("Test", "Test Academy", "", "Bangkok", "", "Bang Bon", driver, wait);


        //############## Verify all inputs ##############//
        System.out.println("***************************************");
        System.out.println("*      [VALIDATION BEFORE DELETE]     *");
        System.out.println("***************************************");

        //verify ID card info
        waitAndClickById("documentIcon", wait);
        verifyCardInfo("Hello", "Monday", "", "3876014697907", "AA1234567890", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify address info
        waitAndClickById("residence_icon", wait);
        verifyAddressInfo("Chartered Square Building", "Sathorn", "Bangkok", "Bang Bon",
                "Chartered Square Building", "Sathorn", "Bangkok", "Bang Bon", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify selected job info
        waitAndClickById("job_title_icon", wait);
        verifyJobinfo("Others", "Test", "Test Academy", "Bangkok", "Bang Bon", driver);

        clickElement("submit_button", driver);
        System.out.println("[Complete] : All validation");

        //*** Delete all data ***//
        waitAndClickById("delele_kyc", wait);
        waitAndClickById("btn_positive", wait);

        //  Check if return to take ID photo page
        if (isElementPresent("take_id_text", driver)) {
            System.out.println("[Complete] : >>> Delete all data <<<");
            System.out.println("========================================");
        }

        //############## Take ID photo ##############//
        System.out.println("[Start] : Take a national ID photo");
        waitAndClickById("take_id_text", wait);
        doubleTap("button_capture", driver);
        waitAndClickById("kyc_confirmation_confirm_btn", wait);
        System.out.println("[Complete] : Take a national ID photo");


        //############## Take selfie photo ##############//
        System.out.println("[Start] : Take selfie with ID photo");
        waitAndClickById("take_selfie_text", wait);
        doubleTap("button_capture", driver);
        waitAndClickById("kyc_confirmation_confirm_btn", wait);
        System.out.println("[Complete] : Take selfie with ID photo");

        //############## Verify all inputs ##############//
        System.out.println("***************************************");
        System.out.println("*      [VALIDATION AFTER DELETE]      *");
        System.out.println("***************************************");

        //verify ID card info
        waitAndClickById("documentIcon", wait);

        verifyCardInfo("(e.g. Somchai)", "(e.g. Sutthiwat)", "(e.g. 01 Jan 2000)", "(e.g. 1234567890123)", "(e.g. XX1-2345678-90)", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify address info
        waitAndClickById("residence_icon", wait);
        verifyAddressInfo("(e.g. house/room number, building)", "(e.g. street name, village)", "Please select province", "Please select city/town",
                "(e.g. house/room number, building)", "(e.g. street name, village)", "Please select province", "Please select city/town", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));


        //verify selected job info
        waitAndClickById("job_title_icon", wait);
        verifyJobinfo("(e.g. Manager)", "", "Where do you work?", "Please select province", "Please select city/town", driver);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        System.out.println("[Complete] : Verify all data");

    }

    //Fill and delete
    @Test
    public void Testcase05_RetakePhoto () throws InterruptedException {

        //############## Click to launch KYC process ##############//
        System.out.println("//##########################################################//");
        System.out.println("// Testcase: Testcase05_RetakePhoto");
        System.out.println("// Objective: To verify if user can click retake photo");
        System.out.println("//##########################################################//");
        waitAndClickById("goPayLauncherPagerImage", wait);
        waitAndClickById("upgrade_now", wait);

        //############## Take ID photo ##############//
        System.out.println("[Start] : Take a national ID photo");
        waitAndClickById("take_id_text", wait);
        waitAndClickById("btn_positive", wait);
        clickAllowPermission(wait);
        doubleTap("button_capture", driver);
        System.out.println("[Complete] : Take a national ID photo");

        //Click retake button
        System.out.println("[Start] : Re-take a national ID photo");
        waitAndClickById("kyc_confirmation_retake_btn", wait);
        boolean IDInstructionText = driver.findElementById("com.get.consumer.app.staging:id/auto_texture_view").isDisplayed();
        //Assert.assertTrue(IDInstructionText, "Retake button does not work");

        try {
            Assert.assertTrue(IDInstructionText);
            System.out.println("[Result]: Retake National ID - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Retake National ID - FAILED");
            throw e;
        }
        /* try { doubleTap("button_capture", driver);
        } catch (Exception e) { }*/
        waitAndClickById("button_capture", wait);
        waitAndClickById("kyc_confirmation_confirm_btn", wait);
        System.out.println("[Complete] : Re-take a national ID photo");


        //############## Take selfie photo ##############//
        System.out.println("[Start] : Take selfie with ID photo");
        waitAndClickById("take_selfie_text", wait);
        doubleTap("button_capture", driver);
        System.out.println("[Complete] : Take selfie with ID photo");

        System.out.println("[Start] : Re-take a selfie with ID photo");
        waitAndClickById("kyc_confirmation_retake_btn", wait);
        boolean SelfieInstructionText = driver.findElementById("com.get.consumer.app.staging:id/auto_texture_view").isDisplayed();
        //Assert.assertTrue(SelfieInstructionText, "Retake button does not work");

        try {
            Assert.assertTrue(SelfieInstructionText);
            System.out.println("[Result]: Retake National ID - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Retake National ID - FAILED");
            throw e;
        }
        /* try { doubleTap("button_capture", driver);
        } catch (Exception e) { }*/
        waitAndClickById("button_capture", wait);
        waitAndClickById("kyc_confirmation_confirm_btn", wait);
        System.out.println("[Complete] : Re-take a selfie with ID photo");

    }

    @Test
    public void Testcase06_InvalidIDAndLaserNumber() throws InterruptedException {

        //############## Click to launch KYC process ##############//
        System.out.println("//##########################################################//");
        System.out.println("// Testcase: Testcase06_InvalidIDAndLaserNumber");
        System.out.println("// Objective: To verify if user fill in invalid card ID and Laser number, error message should display");
        System.out.println("//##########################################################//");
        waitAndClickById("goPayLauncherPagerImage", wait);
        waitAndClickById("upgrade_now", wait);

        //############## Take photo ##############//
        takeIDPhoto(driver, wait);
        takeSelfiePhoto(driver, wait);


        //############## Type in ID card info ##############//
        System.out.println("[Start] : Fill in ID and Laser number");
        waitAndClickById("documentIcon", wait);

        if (isElementPresent("firstname_title", driver)) {
            System.out.println("[Validation]: ID card info form displays as expected");
        } else {
            System.out.println("[Validation]: ID card ifno form does not display as expected");
        }

        fillText("card_number_text_view", "11111", driver);
        scrollDown(330, 315, driver);
        fillText("laser_number_text_view", "11111", driver);

        clickElement("submit_button", driver);
        System.out.println("[Complete] : Fill in ID card info");


        //############## Validation ##############//
        System.out.println("**************************");
        System.out.println("*      [VALIDATION]      *");
        System.out.println("**************************");

        boolean cardErrorMsg = driver.findElementById("com.get.consumer.app.staging:id/card_id_error_text_view").isDisplayed();
        boolean laserErrorMsg = driver.findElementById("com.get.consumer.app.staging:id/laser_id_error_text_view").isDisplayed();

        if (cardErrorMsg) {
            System.out.println("[Validation]: Card ID error message displays as expected");
        } else { System.out.println("[Validation]: Card ID error message does not display as expected"); }

        if (laserErrorMsg) {
            System.out.println("[Validation]: Laser ID error message displays as expected");
        } else { System.out.println("[Validation]: Laser ID error message does not display as expected"); }

        Assert.assertTrue(cardErrorMsg);
        Assert.assertTrue(laserErrorMsg);

    }


    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
