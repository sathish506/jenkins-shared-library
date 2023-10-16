def lintchecks() {
    sh "echo Installing JSlist"
                sh "npm i jslint"
                sh "echo starting link checks......"
                sh "node_modules/jslint/bin/jslint.js server.js || true"
                sh "echo link checks completed"
    }

def call() {
  pipeline {
    agent any 
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
        }
    }
}