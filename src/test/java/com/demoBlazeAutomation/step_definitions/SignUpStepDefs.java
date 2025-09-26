package com.demoBlazeAutomation.step_definitions;

import com.demoBlazeAutomation.pages.SignUpPage;
import com.demoBlazeAutomation.utilities.BrowserUtils;
import com.demoBlazeAutomation.utilities.ConfigurationReader;
import com.demoBlazeAutomation.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpStepDefs {

    SignUpPage signUpPage = new SignUpPage();

    @Given("the user is on the DemoBlaze homepage")
    public void navigateToHomePage() {
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);
    }

    @When("the user clicks the sign up")
    public void clickSignUp() {

        signUpPage.clickOnSignUp();
    }

    @And("the user signs up with username {string} and password {string}")
    public void completeSignUp(String usernameKey, String passwordKey) {
       String username="";
       String password="";

        if (usernameKey.equals("signup_username")){
        signUpPage.signUp();
        BrowserUtils.getAlert().accept();
        }else {
             username = ConfigurationReader.getProperty(usernameKey);
             password = ConfigurationReader.getProperty(passwordKey);
            signUpPage.signUp(username, password);
            BrowserUtils.getAlert().accept();
        }


    }

    @And("the user enters signup information as username {string} and password {string}")
    public void enterSignUpInformation(String usernameKey, String passwordKey) {
        String username="";
        String password="";

        if (usernameKey.equals("signup_username")){
            signUpPage.signUp();

        }else {
            username = ConfigurationReader.getProperty(usernameKey);
            password = ConfigurationReader.getProperty(passwordKey);
            signUpPage.signUp(username, password);

        }


    }

    @Then("an alert with message {string} should be displayed")
    public void anAlertWithMessageShouldBeDisplayed(String expectedMessage) {
        String actualText = BrowserUtils.getAlert().getText();
        Assert.assertEquals(expectedMessage, actualText);

    }


}
