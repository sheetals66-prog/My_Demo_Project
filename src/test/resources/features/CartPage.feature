Feature: CartPage Functionality

  Background:
    Given User searches for  product "kids"
    And User selects a product from PLP
    And User adds product to bag and navigates to cart page

  Scenario: Verify Place Order button is visible
    Then User should see Place Order button

  Scenario: Verify Place Order button is clickable
    When User clicks on Place Order button
    Then User should be navigated to checkout page

  Scenario: Verify price details are displayed
    Then User should see price details on cart page

  Scenario: Verify user can remove product from cart
    When User removes the product
    Then Product should be removed from cart

  Scenario: Verify user can move product to wishlist
    When User moves product to wishlist
    Then Product should be removed from cart

  Scenario: Verify cancel move to wishlist
    When User cancels wishlist move
    Then Product should remain in cart

  Scenario: Verify coupon can be applied
    When User applies coupon
    Then Coupon should be applied successfully

  Scenario: Verify error when adding product without selecting size
    Given User selects product without size
    When User clicks Add to Bag
    Then Error message should be displayed

  Scenario: Verify invalid coupon code
    When User enters invalid coupon "INVALID123"
    Then Error message should be shown
