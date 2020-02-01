@PostManEcho
Feature:
  Background:
    * url "https://postman-echo.com/"

  @echo_Auth
  Scenario: Validate postman-echo endpoint with basic form authentication and header
    * path "basic-auth"
    * form field Username = "postman"
    * form field Password = "password"
    * header Authorization = "Basic cG9zdG1hbjpwYXNzd29yZA=="
    * method GET
    * match response == {authenticated: true}
