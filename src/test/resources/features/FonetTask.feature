Feature: Fonet Test Automation WebUI Task

  Background: User opens the site
    Given The user opens the demoblaze.com site
    When The user checks the site title is "STORE"

  Scenario: Verify total number of products
    And All listed products are fetched
    And Products in the Phones category are fetched
    And Products in the Laptops category are fetched
    And Products in the Monitors category are fetched
    Then Product details and quantities are logged

  Scenario: End-to-End product purchase process
    And User clicks on any product category
    And User clicks on any product card
    And User verifies the product name and price in the product detail page
    And User clicks the Add to cart button
    And User confirms the product added pop-up message
    And User clicks on the Cart menu
    And User verifies the product name and price on the cart menu
    And User verifies the total product price on the cart menu
    And User clicks the "Place Order" button
    And User verifies the price in the Place Order form
    And User fills in the "name" field in the Place Order form
    And User fills in the "country" field in the Place Order form
    And User fills in the "city" field in the Place Order form
    And User fills in the "card" field in the Place Order form
    And User fills in the "month" field in the Place Order form
    And User fills in the "year" field in the Place Order form
    And User clicks the "Purchase" button
    And User verifies the name and the price in the confirmation pop-up
    And User sees the "Thank you for your purchase!" message in the confirmation pop-up
    Then User clicks the "OK" button