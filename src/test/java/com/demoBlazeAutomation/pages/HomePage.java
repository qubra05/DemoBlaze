package com.demoBlazeAutomation.pages;

import com.demoBlazeAutomation.utilities.BrowserUtils;
import com.demoBlazeAutomation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void selectProduct(String productName) {
        By by = By.xpath("//a[contains(text(),'" + productName + "')]");
        WebElement product = BrowserUtils.getElement(by);
        BrowserUtils.waitUntilClickable(product);
        BrowserUtils.click(product);
    }
}

