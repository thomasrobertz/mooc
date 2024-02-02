Feature: Ebay Help Page Scenarios

  @BDD1 # See BasicTestRunner tags option.
  Scenario: Community Question
    Given I am on Ebay Help page
    When I click on Link to ask community
    Then I navigate to Community page