Feature: Cart Functionality Validation

  Background:
    When User searches for "Tshirts"
    And products should be visible on product listing page
    When User selects the product at index 1 from listing
    Then Product detail page should be displayed
    And User selects size "M"
    When User clicks add to bag

  Scenario: Verify product is added to cart
    Then product should be added to cart
    And cart count should be greater than 0

  Scenario: Verify product details in cart
    Then product name should be visible in cart
    And product price should be visible in cart
    And product size and quantity should be visible

  Scenario: Verify place order button
    Then place order button should be visible
    And place order button should be enabled

  Scenario: Verify coupon functionality
    When User clicks coupon button
    And User enters coupon "TEST123"
    And User clicks apply coupon
    Then coupon should be applied or error displayed

  Scenario: Verify remove product from cart
    When User selects product in cart
    And User clicks remove button
    And User confirms remove popup
    Then cart should be empty or updated

  Scenario: Verify move to wishlist
    When User clicks move to wishlist
    And User confirms move to wishlist popup
    Then product should be removed from cart

  Scenario: Verify pincode functionality
    When User enters pincode in cart
    Then delivery address or message should be displayed

  Scenario: Verify max quantity warning
    When User increases quantity to max limit
    Then max quantity warning should be displayed

  Scenario: Verify empty cart message
    Then empty cart message should be displayed (if no products)