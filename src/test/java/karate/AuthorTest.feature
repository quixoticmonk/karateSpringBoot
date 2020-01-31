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


  @Authors
  Scenario: validate author return type
    * path '/api/authors'
    * method GET
    * status 200
    * match $.[*].author == '#[6] #string'
    * match $.[*].author == '#[] #string'


  @Authors
  Scenario: Validate first author
    * path '/api/authors'
    * method GET
    * status 200
    * def authors = get[0] $.[*].author
    * match authors == 'Dan Brown'

