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

    @this
    Scenario: Adding a new patient
      When I login as "regular" user
      And I change language to "english"
      Then I click on patient tab
      And I click on add patient button
      Then User enters all required information to complete the form









#    Then : The request has been successfully created


#  @this
  Scenario: Decline case
    When I login as "expert" user
    And  I check user is on requests page
    When I clicks on Available tab

#    And   : The user clicks "Decline" button on a case
#    Then  : The case should be remove from the "Available" tab
#    And   : The case should be appears in the "Declined" tab

