function fn() {
  var env = karate.env; // get java system property 'karate.env'
  if (!env) {
    env = 'dev';
  }
  var config = {
    baseUrl: 'http://localhost:8080/',
    jsonPlaceHolderUrl : "https://jsonplaceholder.typicode.com/"
  }
  if(env=='staging'){
    config.baseUrl='google.com'
  }
  return config;
}