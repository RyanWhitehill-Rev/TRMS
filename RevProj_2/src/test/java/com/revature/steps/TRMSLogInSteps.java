package com.revature.steps;

import com.revature.pages.TRMSMain;
import com.revature.runners.TRMSRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TRMSLogInSteps {

    public static TRMSMain trmsMain = TRMSRunner.trmsMain;
    public static WebDriver webDriver = TRMSRunner.webDriver;

    @Given("The user can see the login interface")
    public void the_user_can_see_the_login_interface() {
        webDriver.get("file://C:/Users/ryans/Desktop/RP2_fe/myhtml.html");
    }

    @When("^The user inputs valid login credentials$")
    public void the_user_inputs_valid_login_credentials() throws Throwable {
        trmsMain.usernameField.sendKeys("1");
        trmsMain.passwordField.sendKeys("1");
        trmsMain.logInButton.click();
    }

    @Then("^The user should then see the trms interface$")
    public void the_user_should_then_see_the_trms_interface() throws Throwable {
        String expectedDisplay = "block";
        System.out.println("1");

        WebDriverWait wait = new WebDriverWait(webDriver,10);
        WebElement activeUserElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("active_user_div")));

        Assert.assertTrue(activeUserElement.isDisplayed());
    }

}
