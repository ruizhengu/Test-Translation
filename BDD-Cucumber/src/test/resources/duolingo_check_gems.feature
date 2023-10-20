Feature: Does the user have enough gems to enter the character level
  The user must have enough gems to enter the character level

  Scenario: The user successfully enters the character level
    Given The character level costs 350 gems
    And The character level costs 350 gems
    When The user try to enter the character level
    Then The user enters the character level and has 50 gems

