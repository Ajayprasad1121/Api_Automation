Feature: Hotel Search
  As a user
  I want to search for flights and hotels
  So that I can find suitable flight option and accommodations for my travel

  Background: 
    Given I have the base URL "https://ae.almosafer.com/en/"

  Scenario Outline: Search for hotels with filters
    When I search for hotels in "<location>" with the following filters:
      | Filter           | Value          |
      | Search type      | "<SearchType>" |
      | Check-in Date    | "<checkin>"    |
      | Check-out Date   | "<checkout>"   |
      | Number of Guests | "<guests>"     |
    Then I should receive a response with status code <statuscode>
    And the response should contain hotels in "<location>" with the specified filters

    Examples: 
      | SearchType | location  | checkin    | checkout   | guests  | statuscode |
      | hotels     | Dubai     | 09-07-2023 | 10-07-2023 | 2_adult |        200 |
      | hotels     | Abu Dhabi | 10-10-2023 | 15-10-2023 | 1_adult |        200 |
      |            | xyz       | 10-06-2023 | 15-06-2023 | 1_adult |        404 |

  Scenario Outline: Search for flights
    When I search for flights with the following details:
      | SearchOption | origin-destination | departureDate | returnDate | CabinClass | PTC    |
      | flights      | DXB-LHR            | 2023-06-01    | 2023-06-10 | Economy    | 1Adult |
      | flights      | DXB-LHR            | 2023-10-01    | 2023-10-10 | Business   | 1Adult |
      | flights      | DXB-LHR            | 2023-12-01    | 2023-12-31 | First      | 1Adult |
    Then I should receive a response with status code <statuscode>
    And the response should contain flights departing returning on "<flightsdeparting>" and "<flightsreturning>"

    Examples: 
      | flightsdeparting | flightsreturning | statuscode |
      | DXB              | LHR              |        200 |
      | DXB              | LHR              |        200 |
      | DXB              | LHR              |        200 |
