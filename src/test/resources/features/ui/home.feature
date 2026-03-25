@ui
Feature: Home Page
  As a user
  I want to access the home page
  So that I can navigate the application

  @smoke
  Scenario: Home page loads successfully
    Given I open the application home page
    Then the page title is not empty

