Feature: Login functionality

  Background:
    Given I navigate to login page


  Scenario: Login with REGULAR user
    When I login as "regular" user
    Then I check user is logged in

  Scenario Outline: Login with ALL user
    When I login as <user> user
    Then I check user is logged in

    Examples:
      | user      |
      | "regular" |
      | "expert"  |
      | "admin"   |

  Scenario: Login with wrong credentials
    And I fill in email ""
    And I fill in password ""
    And I click on login button
    Then I check error message is displayed