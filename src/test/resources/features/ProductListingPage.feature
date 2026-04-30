Feature:Product Listing Page Functionality

  Background:
    Given User searches for "Tshirts"

  @plpValidation
  Scenario: Verify that Product Listing page is loaded after searching product
    Then product count should be greater than 0
    And  products should be visible on product listing page

  @categoryFilter
  Scenario Outline: Verify Category functionality of Kids products
    When User applies category filter "<category>"
    Then User should see "<category>" category filter applied in URL
  # Category DataProvider for Cucumber Scenario Outline Examples

    Examples:
      | category                   |
      | Aprons                     |
      | Activity Toys and Games    |
      | Bedsheets                  |
      | Bodysuit                   |
      | Booties                    |
      | Backpacks                  |
      | Briefs                     |
      | Blazers                    |
      | Bath Towels                |
      | Baby Apparel Gift Set      |
      | Baby Gear & Nursery        |
      | Baby Utensils              |
      | Bibs                       |
      | Belts                      |
      | Boots                      |
      | Bed Covers                 |
      | Baby Carriers              |
      | Clothing Set               |
      | Casual Shoes               |
      | Caps                       |
      | Co-Ords                    |
      | Curtains and Sheers        |
      | Dungarees                  |
      | Diapers                    |

  @colorFilter
  Scenario Outline: Verify colour filter functionality on PLP page for Tshirts
    When User applies colour filter "<colour>"
    Then User should see "<colour>" colour filter applied in URL
  # Colour DataProvider for Cucumber Scenario Outline

    Examples:
      | colour       |
      | White        |
      | Blue         |
      | Black        |
      | Brown        |
      | Red          |
      | Green        |
      
     
     

  @discountFilter
  Scenario Outline: Verify discount filter functionality on PLP page for Tshirts
    When User applies discount filter "<discount>"
    Then User should see "<discount>" discount filter applied in URL
  # Discount DataProvider for Cucumber Scenario Outline

    Examples:
      | discount      |
      | 10% and above |
      | 20% and above |
      | 30% and above |
      | 40% and above |
      | 50% and above |
      | 60% and above |
      | 70% and above |
      | 80% and above |
      | 90% and above |

  