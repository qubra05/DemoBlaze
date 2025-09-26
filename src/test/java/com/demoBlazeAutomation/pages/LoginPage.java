package com.demoBlazeAutomation.pages;


import com.demoBlazeAutomation.utilities.BrowserUtils;
import com.demoBlazeAutomation.utilities.ConfigurationReader;
import com.demoBlazeAutomation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.demoBlazeAutomation.utilities.BrowserUtils.click;

public class LoginPage {

    public LoginPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "loginusername")
    public WebElement userName;

    @FindBy(id = "loginpassword")
    public WebElement password;

    @FindBy(xpath = "//button[text()='Log in']")
    public WebElement loginBtn;

    @FindBy(id = "login2")
    public WebElement loginLink;

    @FindBy(id = "nameofuser")
    public WebElement loggedInUser;

    public void navigateToHomePage() {
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);
    }

    public void login(String userNameStr, String passwordStr) {
        BrowserUtils.sleep(3);
        loginLink.click();
        userName.sendKeys(userNameStr);
        password.sendKeys(passwordStr);
        click(loginBtn);
    }

    public void verifySuccessfulLogin() {
        BrowserUtils.waitUntilVisibility(loggedInUser);
    }
}
