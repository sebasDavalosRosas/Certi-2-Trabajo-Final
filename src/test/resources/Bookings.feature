Feature: Booking endpoints
  Background: Booking endpoints should allow to get, create, update and delete employees

  Scenario: /Booking should return all the bookings
    Given I perform a GET call to the booking endpoint
    Then I verify that the status code is 200
    And I verify that the body does not have size 0

  Scenario: /booking/{id} should return an specific employee
    Given I perform a GET call to the booking endpoint with id "1"
    Then I verify that the status code is 200
    And I verify that the body does not have size 0
    And The booking name is "Mary"

  Scenario: /booking/{id} with invalid id should return an 404 error
    Given I perform a GET call to the booking endpoint with id "0"
    Then I verify that the status code is 404
    And I do a log on the body

  Scenario Outline: /create should create a booking
    Given I perform a POST call to the create a booking with the following data
      | <first name> | <last name> | <price> | <deposit paid> | <checkin> | <checkout> | aditions |
    Then I verify that the status code is 200
    And I verify that the body does not have size 0
    And The message is "Successfully! Record has been added."
    Examples:
      | <first name> | <last name> | <price> | <deposit paid> | <checkin>  | <checkout> | aditions  |
      | Mauricio     | davalos     | 6000    | true           | 01/01/2025 | 02/01/2025 | no        |
      | Jorge        | rosas       | 3500    | true           | 01/01/2025 | 02/01/2025 | breakfast |

  Scenario Outline: /create with wrong data should not create a booking
    Given I perform a POST call to the create a booking with the following data
      | <first name> | <last name> | <price> | <deposit paid> | <checkin> | <checkout> | aditions |
    Then I verify that the status code is 404
    And I verify that the body does not have size 0
    And The message is "Successfully! Record has been added."
    Examples:
      | <first name> | <last name> | <price> | <deposit paid> | <checkin> | <checkout> | aditions  |
      | Mauricio     | davalos     | 6000    | true           | hola      | hola       | no        |
      | Jorge        | rosas       | 3500    | true           | chau      | chau       | breakfast |