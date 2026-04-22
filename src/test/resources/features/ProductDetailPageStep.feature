@ProductDetail
Feature: Product Detail Page Functionality

  Background:
    When User searches for "Tshirts"
    And products should be visible on product listing page

  @pdpValidation
  Scenario: Verify Product Detail Page loads when a product is selected
    When User selects the product at index 0 from listing
    Then Product detail page should be displayed
    And product title should be visible
    And product price should be visible
    And product images should be visible

  Scenario: Verify size selection on Product Detail Page
    When User selects the product at index 0 from listing
    Then Product detail page should be displayed
    When User selects size 
    Then selected size should be highlighted
    And product availability for selected size should be displayed


  Scenario: Verify Add to Bag from Product Detail Page
    When User selects the product at index 0 from listing
    Then Product detail page should be displayed
    When User selects size 
    And User clicks add to bag
    Then product should be added to cart
    And cart count should increase by 1

  Scenario Outline: Verify different sizes availability on PDP
    When User selects the product at index 0 from listing
    Then Product detail page should be displayed
    When User selects size "<size>"
    Then product availability for selected size should be displayed

    Examples:
      | size     |
      | 3-4Y     |
      | 5-6Y     |
      | 7-8Y     |
      | 8Y-10Y   |
      | 10Y-12Y  |

  Scenario: Verify PDP shows no results message for unavailable product
    When User searches for "asdjkl123-invalid"
    Then no results message should be displayed on listing page
