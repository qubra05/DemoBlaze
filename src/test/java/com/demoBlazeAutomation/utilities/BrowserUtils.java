package com.demoBlazeAutomation.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtils {

    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
        }
    }

    public static void waitUntilVisibility(WebElement webElement) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilClickable(WebElement webElement) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static WebElement getElement(By productLocator) {
        return Driver.getDriver().findElement(productLocator);
    }

    public static void click(WebElement element) {
        waitUntilClickable(element);
        element.click();
    }

    public static void waitUntilStaleness(WebElement element) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.stalenessOf(element));
    }

    public static Alert getAlert(){


        try {
            sleep(3);
            return Driver.getDriver().switchTo().alert();
        }catch (Exception e){

        }
      return null;
    }
}
