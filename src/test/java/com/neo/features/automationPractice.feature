Feature: This file contains the feature

  @Test
  Scenario: Order a product
    Given The "SignInUser" launch the application
    When the user log in to the application
    And the user clicks "Summer Dresses"
    And the user changes view mode to "list"
    And the user clicks product quick view button
    And click on facebook share button
    And the user add the product to cart
    And the user verifies the price details
    And the user clicks on proceed to checkout button
    And The user verifies the order details
    And the user clicks on proceed to checkout button
    And the user clicks on proceed to checkout button
    And the user agree the terms and conditions and checkout
    And the user verifies the amount on order confirmation page

  @Test2
  Scenario: A dummy test Scenario
    Given The "SignInUser" launch the application
    When the user log in to the application
    And the user clicks "Summer Dresses"
    And the user changes view mode to "list"
    And the user clicks product quick view button

  @Test2
  Scenario: A dummy Test Scenario
    Given The "SignInUser" launch the application
    When the user log in to the application
    And the user clicks "Summer Dresses"
    And the user changes view mode to "list"
    And the user clicks product quick view button
