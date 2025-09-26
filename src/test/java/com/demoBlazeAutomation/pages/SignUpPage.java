package com.demoBlazeAutomation.pages;

import com.demoBlazeAutomation.utilities.BrowserUtils;
import com.demoBlazeAutomation.utilities.Driver;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    public SignUpPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "signin2")
    public WebElement signUpLink;

    @FindBy(id = "sign-username")
    public WebElement usernameInput;

    @FindBy(id = "sign-password")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Sign up']")
    public WebElement signUpButton;

    public void clickOnSignUp() {
        signUpLink.click();
    }


    public void signUp(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signUpButton.click();
        BrowserUtils.sleep(3);
    }

    public Pair<String, String> signUp() {
        new LoginPage().navigateToHomePage();
        clickOnSignUp();
        String username = new Faker().name().username();
        String password = new Faker().internet().password();
        signUp(username,password);
        return Pair.of(username, password);
    }
}
