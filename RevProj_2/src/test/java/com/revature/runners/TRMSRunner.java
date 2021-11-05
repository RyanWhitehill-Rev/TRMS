package com.revature.runners;

import com.revature.pages.TRMSMain;
import com.revature.util.LogUtil;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = {"com.revature.steps"})
public class TRMSRunner {

    public static WebDriver webDriver;
    public static TRMSMain trmsMain;

    @BeforeClass
    public static void setup() {
        String path = "C:/Users/ryans/Documents/selenium_drivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);

        webDriver = new ChromeDriver();
        trmsMain = new TRMSMain(webDriver);

    }


    @AfterClass
    public static void teardown() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            LogUtil.logger.info(e);
        }

        webDriver.quit();
    }


}
