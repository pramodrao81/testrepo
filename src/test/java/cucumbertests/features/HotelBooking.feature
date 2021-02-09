#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@BookHotelAndFlights
Feature: Going on holiday
  I want to book flight tickets and a hotel

  Background:
    Given I live in London
    And I want to go on a holiday
    And We are 3 adults
      |Adult1|Adult2 |Adult3|
      |A     |B      |C     |
      |20    |30     |40    |

  @BookHotel
  Scenario: I want to book hotel
    And We want to book from 10th Feb 2019 to 20th Feb 2019
    When I go to travel agent
    Then He should be able to help me in a budget of 1000 USD
    And He should provide me option to cancel
    But He should not ask for advance more than 300 USD


  @BookFlight
  Scenario Outline: I want to book Flight
    And We want to book flight to <Destination> on <FromDate>
    And Return Flight on <ToDate>

    Examples:
      |Destination|FromDate     |ToDate       |
      |Hawai      |10th Feb 2019|20th Feb 2019|
      |Bejing     |21st Apr 2019|25th Apr 2019|