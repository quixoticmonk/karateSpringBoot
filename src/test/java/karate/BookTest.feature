@Books
Feature: BookTest

  Background:
    * url 'http://localhost:8080/'

  @Books
  Scenario: Get all books
    * def responseExpected =
    """
    [
    {"name":"Angels and Demons","author":"Dan Brown"},
    {"name":"Inferno","author":"Dan Brown"},
    {"name":"Harry Potter and the Sorcerer's Stone (Book 1)","author":"J. K. Rowling"},
    {"name":"Harry Potter and the Prisoner of Azkaban","author":"J. K. Rowling"},
    {"name":"The Hobbit","author":"J. R. R. Tolkien"},
    {"name":"1984","author":"George Orwell"},{"name":"Pride and Prejudice ","author":"Jane Austen"},
    {"name":"To Kill a Mockingbird","author":"Harper Lee"}
    ]
    """
    * path '/api/books'
    * method GET
    * status 200
    * match $ == responseExpected


  @Books
  Scenario: Validating the contains matcher
    * path '/api/books'
    * method GET
    * status 200
    * match $ contains {"name":"1984","author":"George Orwell"}


  @Books
  Scenario: Validating the jsonPath
    * def expectedBookNames =
    """
    ["Angels and Demons","Inferno",
    "Harry Potter and the Sorcerer's Stone (Book 1)",
    "Harry Potter and the Prisoner of Azkaban",
    "The Hobbit","1984","Pride and Prejudice ",
    "To Kill a Mockingbird"]
    """
    * path '/api/books'
    * method GET
    * status 200
    * match $.[*].name == expectedBookNames


  @Books
  Scenario: Validating negative Scenario
    * path '/api/books'
    * method GET
    * status 200
    * match $.[*].name != "Istanbul"

  @Books
  Scenario: Validate one book by name
    * path '/api/books/1984'
    * method GET
    * status 200
    * match $ == {"name":"1984","author":"George Orwell"}


  @Books @Authors
  Scenario Outline: Validating the scenario outline ( using __num)
    * def expected = __num==0? '{"name":"1984","author":"George Orwell"}': '{"author":"Jane Austen"}'
    * path '/api/<endpoint>'
    * method GET
    * status 200
    * match $ contains $expected
    Examples:
      | endpoint |
      | books    |
      | authors  |


