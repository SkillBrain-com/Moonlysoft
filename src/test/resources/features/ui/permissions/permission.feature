Feature: Admin page access control

  Background:
    Given I navigate to login page
    And I login as "regular" user
#@this
  Scenario: Regular user cannot access admin page directly
    When I navigate directly to admin page
    Then access should be denied
    And the user should be redirected away from admin page