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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddToCartStepDefs {

    private HomePage homePage = new HomePage();
    private ProductPage productPage = new ProductPage();
    private CartPage cartPage = new CartPage();
    private String selectedProduct;

    @And("I added a product in the cart")
    public void I_added_a_product_in_the_cart() {
        selectedProduct = ConfigurationReader.getProperty("productName");
        homePage.selectProduct(selectedProduct);
        productPage.addProductToCart();
    }

    @Then("the product from config should be visible in the cart")
    public void the_product_from_config_should_be_visible_in_the_cart() {
        cartPage.navigateToCart();
        Assert.assertTrue("Product not found in cart: " + selectedProduct,
                cartPage.isProductInCart(selectedProduct));
    }

}


