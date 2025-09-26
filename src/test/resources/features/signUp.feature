
Feature: User registration (Sign Up)

  Background:
    Given the user is on the DemoBlaze homepage

  @successfulSignup @smoke
  Scenario: Sign up with valid credentials
    When the user clicks the sign up
    And the user enters signup information as username "signup_username" and password "signup_password"
    Then an alert with message "Sign up successful." should be displayed

  @negativeSignup @smoke
  Scenario: Sign up with an existing username
    When the user clicks the sign up
    And the user enters signup information as username "existing_username" and password "signup_password"
    Then an alert with message "This user already exist." should be displayed