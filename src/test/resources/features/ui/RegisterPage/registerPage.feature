@register
Feature: Register Page - Eduson Connect

  Background:
    Given I navigate to register page

  Scenario: Complete registration as Rezident - end to end
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    And I complete second step "Cardiologie", "000000", "Rezident", "Spital Universitar", "Brasov", "Brasov"
    Then agreement dialog is displayed
    When user completes agreement dialog
    Then success message is displayed

  Scenario: Complete registration as Specialist - end to end
    When I complete first step "Andrei", "Popescu", "ignored@test.ro", "Parola123456"
    And I complete second step "Neurologie", "000000", "Specialist", "Spital Județean", "Cluj", "Cluj-Napoca"
    Then agreement dialog is displayed
    When user completes agreement dialog
    Then success message is displayed

  Scenario: Complete registration as Primar - end to end
    When I complete first step "Maria", "Ionescu", "ignored@test.ro", "Parola123456"
    And I complete second step "Pediatrie", "000000", "Primar", "Spital Municipal", "Iași", "Iași"
    Then agreement dialog is displayed
    When user completes agreement dialog
    Then success message is displayed

  Scenario: Agreement dialog appears after step 2 completion
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    And I complete second step "Cardiologie", "000000", "Rezident", "Spital", "Brasov", "Brasov"
    Then agreement dialog is displayed

  Scenario: User reaches step 2 after valid step 1
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    Then user is on step 2

  Scenario: All fields empty on step 1
    When user clicks on Next button
    Then all step 1 error messages are displayed

  Scenario: Completing step 1 without first name
    When user fills in last name with "Cringasu"
    And user fills in email with "cosmin.test@test.ro"
    And user fills in password with "Parola123456"
    And user clicks on Next button
    Then first name error message is displayed

  Scenario: Completing step 1 without last name
    When user fills in first name with "Cosmin"
    And user fills in email with "cosmin.test@test.ro"
    And user fills in password with "Parola123456"
    And user clicks on Next button
    Then last name error message is displayed

  Scenario: Completing step 1 without email
    When user fills in first name with "Cosmin"
    And user fills in last name with "Cringasu"
    And user fills in password with "Parola123456"
    And user clicks on Next button
    Then email error message is displayed

  Scenario: Completing step 1 without password
    When user fills in first name with "Cosmin"
    And user fills in last name with "Cringasu"
    And user fills in email with "cosmin.test@test.ro"
    And user clicks on Next button
    Then password error message is displayed

  Scenario: All fields empty on step 2
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    Then user is on step 2
    When user clicks on Create Account button
    Then all step 2 error messages are displayed

  Scenario: Step 2 without specialty
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    Then user is on step 2
    When user fills in CUIM with "123456"
    And user selects specialty rank "Rezident"
    And user fills in working place with "Spital"
    And user fills in judet with "Brasov"
    And user fills in localitate with "Brasov"
    And user clicks on Create Account button
    Then specialty error message is displayed

  Scenario: Step 2 without CUIM
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    Then user is on step 2
    When user fills in specialty with "Cardiologie"
    And user selects specialty rank "Rezident"
    And user fills in working place with "Spital"
    And user fills in judet with "Brasov"
    And user fills in localitate with "Brasov"
    And user clicks on Create Account button
    Then CUIM error message is displayed

  Scenario: Step 2 without specialty rank
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    Then user is on step 2
    When user fills in specialty with "Cardiologie"
    And user fills in CUIM with "123456"
    And user fills in working place with "Spital"
    And user fills in judet with "Brasov"
    And user fills in localitate with "Brasov"
    And user clicks on Create Account button
    Then specialty rank error message is displayed

  Scenario: Step 2 without working place
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    Then user is on step 2
    When user fills in specialty with "Cardiologie"
    And user fills in CUIM with "123456"
    And user selects specialty rank "Rezident"
    And user fills in judet with "Brasov"
    And user fills in localitate with "Brasov"
    And user clicks on Create Account button
    Then working place error message is displayed

  Scenario: Step 2 without judet
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    Then user is on step 2
    When user fills in specialty with "Cardiologie"
    And user fills in CUIM with "123456"
    And user selects specialty rank "Rezident"
    And user fills in working place with "Spital"
    And user fills in localitate with "Brasov"
    And user clicks on Create Account button
    Then judet error message is displayed

  Scenario: Step 2 without localitate
    When I complete first step "Cosmin", "Cringasu", "ignored@test.ro", "Parola123456"
    Then user is on step 2
    When user fills in specialty with "Cardiologie"
    And user fills in CUIM with "123456"
    And user selects specialty rank "Rezident"
    And user fills in working place with "Spital"
    And user fills in judet with "Brasov"
    And user clicks on Create Account button
    Then localitate error message is displayed