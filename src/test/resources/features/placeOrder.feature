@placeOrder
Feature: Place an order

  @e2e @smoke
  Scenario: Place order successfully
    Given I am logged in with "customer1"
    And I added a product in the cart
    When I proceed to checkout by filling the order form with valid details
      | Name          | Country    | City      | Credit_Card      | Month | Year |
      | Kubra Camoglu | Birmingham | Smethwick | 1111222233334444 | 01    | 2031 |
    Then the order confirmation should be displayed


  @bugFound @deprecated
  Scenario: Can't proceed when card is empty
    Given I am logged in with new customer "customer2"
    And the cart is empty
    When I navigate to cart page
    Then the place order button is disabled


  Scenario: The cart total is not displayed when cart is empty
    Given I am logged in with new customer "customer3"
    When I add a product
    And delete the product from the cart
    Then the cart total is not displayed when cart is empty

  @bugFound @deprecated
  Scenario: Can't place order with missing address
    Given I am logged in with "customer4"
    And I proceed to place order
    When I leave address field empty
      | Name          | Country | City | Credit_Card      | Month | Year |
      | Kubra Camoglu |         |      | 1111222233334444 | 01    | 2031 |
    Then the place order button is disabled