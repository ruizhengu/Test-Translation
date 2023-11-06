Feature: The user can add an email address when there is no address stored

  Scenario: The user adds an email address
    Given There is no email address stored
    When The user launch the app
    Then The app shows "Add an email address"

    