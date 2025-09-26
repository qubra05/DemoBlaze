package com.demoBlazeAutomation.step_definitions;

import com.demoBlazeAutomation.pages.CartPage;
import com.demoBlazeAutomation.pages.HomePage;
import com.demoBlazeAutomation.pages.ProductPage;
import com.demoBlazeAutomation.utilities.ConfigurationReader;
import com.demoBlazeAutomation.utilities.Driver;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

public class EmptyCartStepDefs {

    private ProductPage productPage = new ProductPage();
    private HomePage homePage = new HomePage();
    private CartPage cartPage = new CartPage();
    private String selectedProduct;

    @And("the cart is empty")
    public void theCartIsEmpty() {
        cartPage.navigateToCart();
        cartPage.clearCartCompletely();
        cartPage.navigateToCart();
        assertTrue("Cart is not empty.", cartPage.isCartEmpty());
    }

    @When("I navigate to cart page")
    public void iNavigateToCartPage() {
        cartPage.navigateToCart();
    }

    @Then("the place order button is disabled")
    public void thePlaceOrderButtonIsDisabled() {
        boolean disabled = !cartPage.placeOrderBtn.isEnabled()
                || cartPage.placeOrderBtn.getAttribute("disabled") != null;
        assertTrue("Expected 'Place Order' to be disabled when cart is empty.", disabled);
    }

    @When("I add a product")
    public void iAddAProduct() {
        selectedProduct = ConfigurationReader.getProperty("productName");
        homePage.selectProduct(selectedProduct);
        productPage.addProductToCart();
    }

    @And("delete the product from the cart")
    public void deleteTheProductFromTheCart() {
        cartPage.navigateToCart();
        cartPage.deleteProductFromCart(selectedProduct);
        System.out.println("Deleted product: " + selectedProduct);
    }

    @Then("the cart total is not displayed when cart is empty")
    public void theCartTotalIsNotDisplayedWhenCartIsEmpty() {

        Assert.assertFalse("Total price should not be visible when cart is empty.",
                cartPage.isTotalAmountVisible());
    }


}

