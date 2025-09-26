package com.demoBlazeAutomation.pages;

import com.demoBlazeAutomation.utilities.BrowserUtils;
import com.demoBlazeAutomation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[text()='Place Order']")
    public WebElement placeOrderBtn;

    @FindBy(id = "name")
    public WebElement nameInput;

    @FindBy(id = "country")
    public WebElement countryInput;

    @FindBy(id = "city")
    public WebElement cityInput;

    @FindBy(id = "card")
    public WebElement cardInput;

    @FindBy(id = "month")
    public WebElement monthInput;

    @FindBy(id = "year")
    public WebElement yearInput;

    //TODO: total amount web Element
    @FindBy(id = "totalp")
    public WebElement totalAmount;

    @FindBy(xpath = "//button[text()='Purchase']")
    public WebElement purchaseBtn;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//h2")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//button[text()='OK']")
    public WebElement okBtn;


    public void navigateToCart() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        if (!Driver.getDriver().getCurrentUrl().contains("cart.html")) {
            System.out.println("Currently not on cart page. Clicking Cart link...");
            WebElement cartLinkDynamic = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("cartur"))
            );
            cartLinkDynamic.click();
        } else {
            System.out.println("Already on cart page. No click needed.");
        }
        wait.until(ExpectedConditions.urlContains("cart.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
    }

    public void clickPlaceOrder() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));
    }

    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        if (name != null && !name.trim().isEmpty()) {
            nameInput.sendKeys(name);
        }

        if (country != null && !country.trim().isEmpty()) {
            countryInput.sendKeys(country);
        }

        if (city != null && !city.trim().isEmpty()) {
            cityInput.sendKeys(city);
        }

        if (card != null && !card.trim().isEmpty()) {
            cardInput.sendKeys(card);
        }

        if (month != null && !month.trim().isEmpty()) {
            monthInput.sendKeys(month);
        }

        if (year != null && !year.trim().isEmpty()) {
            yearInput.sendKeys(year);
        }
    }

    public String getConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        try {
            Thread.sleep(1000); // 1 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return confirmationMessage.getText();
    }

    public void confirmOrder() {
        okBtn.click();
    }

    public boolean isProductInCart(String productName) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));


        //TODO extract xpath to find by
        List<WebElement> products = Driver.getDriver().findElements(
                By.xpath("//tbody[@id='tbodyid']//tr/td[2]")
        );

        if (products.isEmpty()) {
            System.out.println("Cart is empty. No products found.");
            return false;
        }

        for (WebElement product : products) {
            if (product.getText().trim().equalsIgnoreCase(productName.trim())) {
                return true;
            }
        }
        return false;
    }

    public void deleteProductFromCart(String productName) {
        By xpath = By.xpath(String.format("//tbody[@id='tbodyid']//tr[td[2][text()='%s']]//a[text()='Delete']", productName));
        WebElement elementDelete = BrowserUtils.getElement(xpath);
        BrowserUtils.click(elementDelete);
        BrowserUtils.waitUntilStaleness(elementDelete);
    }


    public void clearCartCompletely() {
        navigateToCart();

        WebDriverWait w = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        By rowSel = By.cssSelector("#tbodyid tr");

        List<WebElement> rows = Driver.getDriver().findElements(rowSel);

        while (!rows.isEmpty()) {
            WebElement firstRow = rows.get(0);
            WebElement deleteLink = firstRow.findElement(By.xpath(".//a[normalize-space()='Delete']"));
            deleteLink.click();
            try {
                w.until(ExpectedConditions.stalenessOf(firstRow));
            } catch (TimeoutException e) {
                int before = rows.size();
                w.until(ExpectedConditions.numberOfElementsToBeLessThan(rowSel, before));
            }
            rows = Driver.getDriver().findElements(rowSel);
        }
    }

    public boolean isCartEmpty() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        By rowSel = By.cssSelector("#tbodyid tr");
        wait.until(ExpectedConditions.numberOfElementsToBe(rowSel, 0));
        List<WebElement> rows = Driver.getDriver().findElements(rowSel);
        return rows.isEmpty();
    }

    public boolean isTotalAmountVisible() {
        return !totalAmount.getText().trim().isEmpty();
    }

}


