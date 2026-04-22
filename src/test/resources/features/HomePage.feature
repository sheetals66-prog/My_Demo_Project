Feature: Myntra HomePage Functionality

Scenario: Verify homePage of Myntra loads
  Given user is on homepage
  Then homepage should be displayed


@searchfunctionality
Scenario: Verify search functionality of different products
  Given user is on homepage
  When user searches for "Tshirts"
  Then product results should be displayed


Scenario: Verify search with special characters
  Given user is on homepage
  When user searches for "@#$%^&*()"
  Then no results message should be displayed


Scenario: Verify search with empty input
  Given user is on homepage
  When user searches for ""
  Then no results message should be displayed


Scenario: Verify search with invalid number
  Given user is on homepage
  When user searches for "455555555"
  Then no results message should be displayed


# ---------------- POSITIVE ----------------

Scenario Outline: Search different products on Myntra
  Given user is on homepage
  When user searches for "<product>"
  Then product results should be displayed

Examples:
  | product     |
  | T-shirts    |
  | shirts      |
  | party wear  |
  | tops        |
  | kurta sets  |
  | flip-flops  |
  | flats       |
  | soft toys   |
  | body suits  |
  | bottom wear |
  | bags        |
  | watches     |
  | sunglasses  |
  | infant care |
  | sandals     |


# ---------------- AUTOSUGGEST ----------------

Scenario: Verify search suggestions are displayed while typing
  Given user is on homepage
  When user types keyword "Tshirts"
  Then user should see the autosuggestions


# ---------------- WISHLIST ----------------

Scenario: Verify wishlist without login
  Given user is on homepage
  When user clicks on wishlist icon
  Then user should be redirected to the login page


# ---------------- ORDERS ----------------

Scenario: Verify orders list without login
  Given user is on homepage
  When user clicks on orders list
  Then user should be redirected to the login page