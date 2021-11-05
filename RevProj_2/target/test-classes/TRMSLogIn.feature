Feature: Log in to the trms

  Scenario: Login functionality works
    Given The user can see the login interface
    When The user inputs valid login credentials
    Then The user should then see the trms interface