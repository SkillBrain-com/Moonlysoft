Feature: E2E Flow
  Background:
    Given I navigate to register page

    Scenario: I complete happy flow
      When I complete first step "Darius", "Test", "test@spital.ro", "test1234"
      And I complete second step "Cardiologie", "123456", "Rezident", "Spital", "Brasov", "Brasov"
