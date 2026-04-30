Feature: Feature: Validate Myntra Kids Section Navigation

  This feature verifies UI navigation flow for the Myntra Kids section.

  It ensures:
  - Homepage loads successfully
  - Core UI elements are visible (logo, search bar, wishlist icon, Kids tab)
  - User can navigate to Kids page via UI click and direct URL access

  Background:
    When user open the url

  Scenario:
     Verify that the Myntra homepage loads successfully and the logo is visible

    Then home page should be display

  Scenario:
    Verify that the search bar is visible on the homepage of Myntra

    Then search bar should be visible on homepage

  Scenario:
    Verify that the wishlist icon is visible on the homepage

    Then WishListIcon should be visible

  Scenario: Verify Kids page loads via direct URL
    Then Kids page should be displayed successfully

  Scenario:
      Verify that clicking the 'Kids' on search bar and kids page.

    And search kids product on search bar
    Then kids page should be display

