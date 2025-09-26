package com.demoBlazeAutomation.step_definitions;

import com.demoBlazeAutomation.pages.CartPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.Map;

public class MissingFieldsStepDefs {

    CartPage cartPage = new CartPage();

    @And("I proceed to place order")
    public void iProceedToPlaceOrder() {
        cartPage.navigateToCart();
        cartPage.clickPlaceOrder();
    }

    @When("I leave address field empty")
    public void i_leave_address_field_empty(DataTable table) {
        Map<String, String> data = table.asMaps(String.class, String.class).get(0);

        System.out.println("DataTable content: " + data); //DEBUG

        cartPage.fillOrderForm(
                data.get("Name"),
                data.get("Country"),
                data.get("City"),
                data.get("Credit_Card"),
                data.get("Month"),
                data.get("Year"));

    }
}
