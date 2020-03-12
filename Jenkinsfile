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
    stage("Initialize"){
      steps{
        sh"java -version"
      }
    }
    stage('Build'){
      steps{
          sh "mvn clean compile -DskipTests=true"
      }
    }
    stage("Quality Gates"){
      parallel {
        stage('Karate Tests'){
          steps{
            sh "mvn test-compile surefire:test -Dtest=TestRunner"
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/target/surefire-reports/', reportFiles: '*.html', reportName: 'Karate test report', reportTitles: ''])
          }
        }

        stage('Gatling tests'){
          steps{
            sh "mvn gatling:test"
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/target/gatling/**/', reportFiles: 'index.html', reportName: 'Gatling analysis report', reportTitles: ''])
          }
        }

        stage('Dependency Check'){
          steps{
            dependencyCheck additionalArguments: '', odcInstallation: 'depCheck'
            dependencyCheckPublisher pattern: ''
          }
        }

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