'''
################# USER DEFINED VARIABLES###############################
'''
def CF_ORG = "columbus-apps"
def CF_SPACE = "jenkins-demo"
def JOB_NAME = "${env.JOB_NAME}".tokenize('/')[0]
def BRANCH = "${env.JOB_NAME}".tokenize('/')[1]
def JENKINS_NOTIFICATIONS_TO = "manu.chandrasekhar@accenture.com"
'''
################# USER DEFINED VARIABLES###############################
'''

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
        stage("Initialize") {
            steps {
                sh "java -version"
                sh "mvn -version"
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests=true"
            }
        }
        stage('Compile test classes') {
            steps {
                sh "mvn test-compile"
            }
        }
        stage("Quality Gates") {
            parallel {
                stage('Karate Tests') {
                    steps {
                        sh "mvn surefire:test -Dtest=TestRunner"
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/cucumber-html-reports', reportFiles: 'overview-features.html', reportName: 'Karate test run report', reportTitles: ''])
                        //publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/target/surefire-reports/', reportFiles: '*.html', reportName: 'Karate test report', reportTitles: ''])
                    }
                }

                stage('Gatling tests') {
                    steps {
                        sh "mvn gatling:test"
                        gatlingArchive()
                    }
                }

                stage('Dependency Check') {
                    steps {
                        dependencyCheck additionalArguments: '', odcInstallation: 'depCheck'
                        dependencyCheckPublisher pattern: ''
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([string(credentialsId: 'CF_API', variable: 'CF_API'), string(credentialsId: 'CF_USER', variable: 'CF_USER'), string(credentialsId: 'CF_PASS', variable: 'CF_PASS')]) {
                    sh "cf login -a ${CF_API} -u ${CF_USER} -p ${CF_PASS} -o ${CF_ORG} -s ${CF_SPACE} "
                    sh "cf push jenkins-demo -p target/*.jar "
                }
            }
        }
    }
    post {
        success {
            echo "SUCCESSFUL BUILD"
        }
        failure {
            echo "BUILD FAILED"
        }
        always {
            deleteDir()
        }
    }
}