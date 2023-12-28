@ui
Feature: Catalog Onliner - Comparing feature

  Scenario: User choose some random items for comparing
    Given user open Catalog page on Onliner
    When user redirects to "notebook" section
    And user choose 3 random items on Items page
    And user click Compare button on Items page
    And save actual list of items on Comparison page
    Then user sees 3 items on Comparison page
    Then user sees expected item names on Comparison page

  Scenario: User compare some random items
    Given user open Catalog page on Onliner
    When user redirects to "notebook" section
    And user choose 3 random items on Items page
    And user click Compare button on Items page
    Then user sees correct comparing results as cells highlighting for next values on Comparison page:
      | Launch date      |
      | Storage capacity |

  Scenario: User delete random item
    Given user open Catalog page on Onliner
    When user redirects to "notebook" section
    And user choose 3 random items on Items page
    And user click Compare button on Items page
    When user delete random item on Comparison page
    And save actual list of items on Comparison page
    Then user sees 2 items on Comparison page
