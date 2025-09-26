package com.demoBlazeAutomation.pages;

import com.demoBlazeAutomation.utilities.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    public ProductPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[text()='Add to cart']")
    public WebElement addToCartBtn;

    public void addProductToCart() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        // "Add to cart" butonunun tıklanabilir olmasını bekle
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

        // Alert'i bekle ve kabul et
        wait.until(ExpectedConditions.alertIsPresent());
        Driver.getDriver().switchTo().alert().accept();

        System.out.println("Product successfully added to cart");
    }


}
