@ui
Feature: Home Page

  Background:
    Given I navigate to login page

  Scenario: Add filters on the Dashboard
    When I login as "expert" user
    And  I check user is logged in
    Then I click on filters
    And  The user clicks on Status "Deschis"
    Then the page should displays only the selected status


  Scenario: Adding a new patient
    When I login as "regular" user
    And I change language to "english"
    Then I click on patient tab
    And I click on add patient button
    And I fill date with "{date:today:yyyy-MM-dd}"
    Then User enters all required information to complete the form with "Manole","1650512050014","test1@gmail.com","55",""
    And The request has been successfully created

  Scenario: Adding a new patient without CNP
    When I login as "regular" user
    And I change language to "english"
    Then I click on patient tab
    And I click on add patient button
    And I click on submit without filling in te required CNP field
    Then A warning message should be appears


  Scenario: Adding a new request
    When I login as "regular" user
    And I change language to "english"
    Then  I click on new request
    And I fill in the general info with "Back pain","The patient presents with upper right back pain","Popescu"
    And I go to the next page expert assignment
    And I choose the expert to assignment to request
    Then I click on create request button
    And I check that the last created case ID is incremented by 1 compared to the previous ID
#    testul pica la validarea incrementarii

  @this
  Scenario: Decline case
    When I login as "expert" user
    And I change language to "english"
    And I check user is on requests page
    Then I clicks on Available tab
    And I check the number of available cases 3
    And user completes log out process
#    Then I check user is logged out
    And I navigate to login page
    When I login as "regular" user
    And I change language to "english"
    Then I click on new request
    And I fill in the general info with "Back pain","The patient presents with upper right back pain","Manole"
    And I go to the next page expert assignment
    And I choose the expert to assignment to request
    Then I click on create request button
    And user completes log out process
#    And I check user is logged out
    When I login as "expert" user
    And  I check user is on requests page
    Then I clicks on Available tab
#    And I click on "Decline" button on case






#    Then  : The case should be remove from the "Available" tab
#    And   : The case should be appears in the "Declined" tab

