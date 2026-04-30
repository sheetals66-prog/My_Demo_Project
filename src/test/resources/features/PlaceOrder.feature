
 Feature: Place Order functionality

  Scenario: User searches product and places order successfully
    Given user searches for product "Kids"
    And user applies Boys filter on PLP
    And user applies Tshirts category filter
    And user selects a product from PLP
    And user selects product size if available
    And user adds product to bag and navigates to cart page
    When user applies coupon "SAVE10" on cart page
    And user clicks on Place Order button
    Then user should be navigated to checkout page