Feature:

  Background:
    * url 'http://localhost:8080/'

  @Authors
  Scenario: Validate authors endpoint
    * path '/api/authors'
    * method GET
    * status 200
    * match $.[*].author contains "George Orwell"

