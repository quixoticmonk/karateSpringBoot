pipeline {
  agent any
    tools {
      maven "maven"
      jdk "jdk"
    }

  options {
    disableConcurrentBuilds()
    timeout(time: 1, unit: 'HOURS')
    skipDefaultCheckout(false)
    parallelsAlwaysFailFast()
    buildDiscarder(logRotator(numToKeepStr: '15'))
  }

  stages {
    stage('Initialize'){
      steps{
        sh"java -version"
      }
    }
    stage('Build artificat'){
      steps{
          sh "mvn clean compile -DskipTests=true"
      }
    }
    stage('Run Karate Tests'){
      steps{
        sh "mvn test-compile surefire:test -Dtest=TestRunner"
      }
    }

    stage('Run Gatling tests'){
      steps{
        sh "mvn gatling:test"
      }
    }
    stage('Dependency Check'){
      steps{
          dependencyCheck additionalArguments: '', odcInstallation: 'depCheck'
          dependencyCheckPublisher pattern: ''
      }
    }


  }

  post{
    success{
      echo"SUCCESSFUL BUILD"
    }
    failure{
      echo "BUILD FAILED"
    }
    always{
      junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
      deleteDir()
    }
  }
}