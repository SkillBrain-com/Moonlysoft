Feature: Requests functionality

  Background:
    Given I navigate to login page
    When I login as "expert" user
    And  I navigate to requests page

    Scenario:
      When I click on Available tab
      And I click on Take case button
      Then the case should be in Active tab
      And the Active tab counter should be updated

#    create new request
#  dashboard - request
#  click on available tab
#    check number is updated