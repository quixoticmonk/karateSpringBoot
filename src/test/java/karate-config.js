function fn() {
  var env = karate.env; // get java system property 'karate.env'
  if (!env) {
    env = 'dev';
  }
  var config = {
    someUrlBase: 'https://some-host.com/v1/auth/'
  };


  return config;
}