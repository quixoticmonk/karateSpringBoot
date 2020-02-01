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



