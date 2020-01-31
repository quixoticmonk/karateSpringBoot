@AuthorTest
Feature:

  Background:
    * url baseUrl

  @Authors
  Scenario: Validate authors endpoint
    * path '/api/authors'
    * method GET
    * status 200
    * match $.[*].author contains "George Orwell"


  @Authors
  Scenario: read author list from a csv
    * path '/api/authors'
    * method GET
    * status 200
    * match $ == read("authors.csv")
