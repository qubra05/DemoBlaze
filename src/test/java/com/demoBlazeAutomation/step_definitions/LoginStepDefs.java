package com.demoBlazeAutomation.step_definitions;

import com.demoBlazeAutomation.pages.LoginPage;
import com.demoBlazeAutomation.pages.SignUpPage;
import com.demoBlazeAutomation.utilities.BrowserUtils;
import com.demoBlazeAutomation.utilities.ConfigurationReader;
import com.demoBlazeAutomation.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStepDefs {

    private LoginPage loginPage = new LoginPage();
    private SignUpPage signUpPage = new SignUpPage();

    @Given("I am logged in with {string}")
    public void i_am_logged_in_with(String key) {
        loginPage.navigateToHomePage();
        String username = ConfigurationReader.getProperty(key);
        String password = ConfigurationReader.getProperty(key + "_password");
        loginPage.login(username, password);
        loginPage.verifySuccessfulLogin();
    }

    @Given("I am logged in with new customer {string}")
    public void i_am_logged_in_with_neCustomer(String key) {
        loginPage.navigateToHomePage();
        signUpPage.clickOnSignUp();
        String username = new Faker().name().username();
        String password = ConfigurationReader.getProperty(key + "_password");
        signUpPage.signUp(username,password);
        BrowserUtils.getAlert().accept();
        loginPage.login(username, password);
        loginPage.verifySuccessfulLogin();
    }

}
