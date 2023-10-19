def lintchecks() {
   
        sh "echo starting link checks for ${COMPONENT}"
        sh "pylint *.py || true"
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
       }
    }
}