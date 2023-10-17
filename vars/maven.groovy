def lintchecks() {
   
        sh "echo starting link checks for ${COMPONENT}"
        sh "echo mvn checkstyle:check || true"
        sh "echo link checks completed for ${COMPONENT}"
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
                sh "echo Generating artifacts for $COMPONENT"
                sh "mvn clean package"

                }
            }
        }
    }
}