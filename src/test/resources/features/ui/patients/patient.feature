Feature: Patient page functionality

  Background:
    Given I navigate to login page
    And I login as "regular" user
    And I change language to "english"
    And I navigate to patient page


  Scenario: Add patient with invalid CNP format
    When I click Add Patient button
    And I fill in full name "Test Patient"
    And I fill in CNP with invalid format
    And I click confirm Add Patient button
    Then an error message should appear on the CNP field


  Scenario: Add patient with valid data - Happy Flow
    When I click Add Patient button
    And I fill in full name "Test Patient Happy Flow"
    And I fill in CNP with valid format
    And I click confirm Add Patient button
    Then the new patient should appear in the patients table


  Scenario: User deletes a patient successfully
    When there is at least one patient in the table
    And the user clicks the Delete button on that patient
    And confirms the deletion in the confirmation dialog
    Then a toast notification should appear

#  @this
  Scenario: Add patient with empty full name field
    When I click Add Patient button
    And I leave the Full Name field empty
    And I fill in CNP with valid format
    And I click confirm Add Patient button
    Then an error message "Name is required" should appear under the Full Name field


#  Scenario Outline: Regular user creates a patient and expert user verifies it
#    Given I login as "regular" user
#    When I navigate to patient page
#    And I click Add Patient button
#    And I fill in full name "<fullName>"
#    And I fill in CNP with "<cnp>"
#    And I click confirm Add Patient button
#    Then the new patient should appear in the patients table
#    When I log out
#    And I log in as an expert user
#    And I navigate to patient page
#    Then the patient "Adrian Adin" should be visible in the table
#
#    Examples:
#      | fullName                | cnp           |
#      | Adrian Adin             | 1234567891233 |
