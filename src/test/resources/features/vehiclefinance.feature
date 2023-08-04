@vehicle_finance
Feature: WEB - Vehicle Finance Feature
  Background:
    Given the user is on the vehicle finance page
    And the vehicle purchase is "120000"
    And the deposit is "12000"
    And the payment term is "54"
    And balloon payment is "20"
    And interest rate is "11"

  Scenario Outline: Validate That the user may calculate vehicle finance
    When the user calculates
    Then the amount to finance is "<amount_to_finance>"
#    And the interest amount is "<interest>"
#    And the total cost of credit is "<total_cost>"

    Examples:
      | amount_to_finance | interest | total_cost |
      | R681 207.50       |          |            |