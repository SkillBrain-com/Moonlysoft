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
#    TODO - implement negative scenarios
    And I fill in email ""
    And I fill in password ""
    And I click on login button
    Then I check error message is displayed


  Scenario: REGULAR user sees correct sidebar menu
    Given the user is logged in as REGULAR
    When the dashboard page is loaded
    Then only Dashboard, Patient and Profile menu items should be visible
    And Requests, Admin and Analytics menu items should not be visible


  Scenario: REGULAR user cannot access Admin page
    Given the user is logged in as REGULAR
    When the user navigates to the admin page
    Then access should be denied
    And the user should not see the admin panel content

  @this
  Scenario: User role updates after refresh
    Given the user is logged in as REGULAR
    And the user role is changed to EXPERT in the system
    When the user refreshes the application
    Then the user should see EXPERT-specific menu items