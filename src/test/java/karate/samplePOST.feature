@PostManEcho
Feature:
  Background:
    * url "https://postman-echo.com/"



  @echo_post
  Scenario: Validate postman-echo endpoint for post
    * path "post"
    * header Content-Type = "application/json"
    * request {foo1:'bar1',foo2:'bar2'}
    * method POST
    * match $ contains {"data":{"foo1":"bar1","foo2":"bar2"}}

  @echo_basicAuth
  Scenario: Validate postman-echo endpoint with basic form authentication and header
    * path "basic-auth"
    * form field Username = "postman"
    * form field Password = "password"
    * header Authorization = "Basic cG9zdG1hbjpwYXNzd29yZA=="
    * method GET
    * match response == {authenticated: true}


  @echo_digestAuth
  Scenario: Validate postman-echo endpoint with digest form authentication and header
    * path "digest-auth"
    * header Authorization = 'Digest username="postman", realm="Users", nonce="ni1LiL0O37PRRhofWdCLmwFsnEtH1lew", uri="/digest-auth", response="254679099562cf07df9b6f5d8d15db44", opaque=""'
    * method GET
    * match response == {authenticated: true}



