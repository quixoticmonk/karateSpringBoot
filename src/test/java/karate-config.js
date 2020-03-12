function fn() {
  var env = karate.env; // get java system property 'karate.env'
  if (!env) {
    env = 'dev';
  }
  var config = {
    baseUrl: 'http://jenkins-demo.apps.apcf.cihpcf.io/',
    jsonPlaceHolderUrl : "https://jsonplaceholder.typicode.com/"
  }
  if(env=='staging'){
    config.baseUrl='google.com'
  }
  return config;
}