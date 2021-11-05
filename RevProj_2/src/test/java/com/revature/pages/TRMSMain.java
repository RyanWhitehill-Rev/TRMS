package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TRMSMain {

    public WebDriver webDriver;

    public TRMSMain(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id="login_username")
    public WebElement usernameField;

    @FindBy(id="login_password")
    public WebElement passwordField;

    @FindBy(id="log_in_button")
    public WebElement logInButton;

    @FindBy(id="active_user_div")
    public WebElement activeUserDiv;




}
