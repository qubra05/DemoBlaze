package com.demoBlazeAutomation.step_definitions;

import com.demoBlazeAutomation.pages.CartPage;
import com.demoBlazeAutomation.utilities.ConfigurationReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class PlaceOrderStepDefs {
    CartPage cartPage = new CartPage();

    @Then("the order confirmation should be displayed")
    public void the_order_confirmation_should_be_displayed() {
        cartPage.purchaseBtn.click();
        System.out.println("Purchase button clicked.");
        String confirmationText = cartPage.getConfirmationMessage();
        System.out.println("Confirmation message: " + confirmationText);

        Assert.assertTrue(
                "Confirmation message not displayed!",
                confirmationText.contains("Thank you for your purchase!")
        );

        cartPage.confirmOrder();
    }

    @When("I proceed to checkout by filling the order form with valid details")
    public void iProceedToCheckoutByFillingTheOrderFormWithValidDetails(DataTable table) {
        Map<String, String> data = table.asMaps(String.class, String.class).get(0);

        cartPage.navigateToCart();
        cartPage.clickPlaceOrder();

        cartPage.fillOrderForm(
                data.get("Name"),
                data.get("Country"),
                data.get("City"),
                data.get("Credit_Card"),
                data.get("Month"),
                data.get("Year"));

        cartPage.purchaseBtn.click();
    }
}
