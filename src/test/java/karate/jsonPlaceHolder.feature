@JsonPlaceHolder
Feature: JsonTests

  Background:
    * url jsonPlaceHolderUrl

  Scenario: Validate the structure of todos output
    * path '/todos'
    * method GET
    * status 200
    * match $[0] == {"userId": "#number" , "id": "#number", "title": "#string" , "completed": "#boolean"}
    #Validating that the number of elements is 200
    * match $ == '#[200]'


  Scenario: Validate the structure of posts output
    * path '/posts/1'
    * method GET
    * status 200
    * match $ == {"userId": "#number" , "id": "#number", "title": "#string" , "body": "#string"}

  Scenario: Validate POST method
    * def requestInput =
    """
    {
      id: 101,
      title: 'foo',
      body: 'bar',
      userId: 1
    }
    """
    * path '/posts'
    * request requestInput
    * header Content-type = "application/json; charset=UTF-8"
    * method POST

