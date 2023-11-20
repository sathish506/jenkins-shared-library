def lintchecks() {
    sh "echo Installing JSlist"
                sh "npm i jslint"
                sh "echo starting link checks for ${COMPONENT}"
                sh "node_modules/jslint/bin/jslint.js server.js || true"
                sh "echo link checks completed for ${COMPONENT}"
    }

def call() {
  pipeline {
    agent any 
    environment {
        SONAR_URL = "172.31.38.134"
        SONAR_CRED = credentials('SONAR_CRED')
    }
    }
    stages {
        stage('Lint Checks') {
            steps {
             script {
                lintchecks()
             }
                
            }
        }

        stage('Generating artifacts') {
            steps {
                sh "echo Generating artifacts"
                sh "npm install"

                }
            }
        stage('sonar checks'){
            steps {
                sh "sonar-scanner -Dsonar.host.url=http://${SONAR_URL}:9000/ -Dsonar.sources=. -Dsonar.projectkey=${COMPONENT} -Dsonar.login=admin -Dsonar.password=password"
            }
           }
        }
    }
