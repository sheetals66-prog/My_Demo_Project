Feature: Product Details Page (PDP) Functionalityz

  Background:
    Given User searches for a product "Tshirts"
    And products are visible on PLP
    When User clicks on a product from PLP


@ProductOnPdp
  Scenario: Verify PDP opens when user clicks on product
    Then Product details page should be displayed

  Scenario: Verify PDP corresponds to selected product on PLP
    Then User should see correct title name on PDP
    And User should see product price on PDP
    And User should see wishlist icon on PDP
    And User should see add to bag button on PDP

  Scenario: Verify user can add a Tshirt to bag from PDP
    And User selects size if required and clicks on Add to Bag button
    Then Go To Bag button should be displayed

  Scenario: Verify wishlist requires login from PDP
    When User clicks on Wishlist icon on PDP
    Then Login page should be displayed
   

  Scenario: Verify PDP shows correct message for valid and invalid pincodes
    Given User searches for a product "Tshirts"
    And products are visible on PLP
    When User clicks on a product from PLP
    Then User verifies pincode messages on PDP


