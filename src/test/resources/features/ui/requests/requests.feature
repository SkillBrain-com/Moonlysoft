Feature: Requests functionality

  Background:
    Given I navigate to login page
    When I login as "expert" user
    And  I navigate to requests page
  @this
    Scenario:
      When I click on Available tab
      And I click on Take case button
      Then the case should be in Active tab
      And the Active tab counter should be updated
