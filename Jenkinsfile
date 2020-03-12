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
        sh "mvn gatling:test -Dgatling.simulationClass=UserSimulation"
      }
    }
    stage('Dependency Check'){
      steps{
          dependencyCheck additionalArguments: '', odcInstallation: 'depCheck'
          dependencyCheckPublisher pattern: ''
      }
    }

    stage('Publish Jacoco Report') {
      steps {
        junit '**/target/surefire-reports/**.xml'
        step([$class: 'JacocoPublisher',
        execPattern: '**/target/**.exec',
        classPattern: '**/target/classes',
        sourcePattern: '**/src/main/java'])
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