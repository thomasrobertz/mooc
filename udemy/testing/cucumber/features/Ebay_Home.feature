# This is a gherkin file. Note NOT every keyword is followed by a colon!
# If you have latest dependencies, a maven project does not need to be further configured,
#   IntelliJ comes with cucumber and gherkin plugins and tests should be ready to run (Right click, run).
#   That's why if you do BDD and create this file first, you will already get Problems (IntelliJ Problems tab, or when running) such as:
#     Undefined step reference: I am on Ebay Home Page
#   But it will be nice and generate snippets for our gherkin definitions to copy paste.
#   Once we have created the steps, we can already run the Feature.
# We don't want to run tests individually, so we create a test runner.
Feature: Ebay Home Page Scenarios

  Scenario: Advanced Search
    Given I am on Ebay Home Page
    When I click on Advanced Link
    Then I navigate to Advanced Search page