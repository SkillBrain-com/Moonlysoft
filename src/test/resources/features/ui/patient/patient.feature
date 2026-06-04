@ui
Feature: Patient Page

Background:
Given I navigate to login page


Scenario: Patient counter update
  Given I login as "regular" user
  And I click on patient tab




#And   : The user is on the "patient" page
#When  : The user clicks on "Add Patient"
#And   : The user fills in all required patient details
#And   : The user clicks on the "Add Patient" button
#Then  : The new patient should be successfully added
#And   : The list of the patients should increase by 1
