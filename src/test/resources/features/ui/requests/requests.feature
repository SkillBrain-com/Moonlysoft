Feature: Requests functionality

  Background:
    Given I navigate to login page
    When I login as "expert" user
    And  I navigate to requests page
    And I change language to "english"


  Scenario:Check Active tab is updated
    When I click on Available tab
    And I click on Take case button
    Then the case should be in Active tab
    And the Active tab counter should be updated

  @this
  Scenario:Check Available tab is updated
    When I click on Available tab
    And I click the Decline button on a case
    Then the case should be removed from the Available tab
    And the tab counter should update correctly


  Scenario: Switch view mode on requests page
    Then the list view mode should be active by default
    When I click on Compact view button
    Then the view should switch to Compact mode
    When I click on Comfortable view button
    Then the view should switch to Comfortable mode