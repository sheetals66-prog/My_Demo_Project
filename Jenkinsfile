pipeline {
    agent any
    environment {
        PATH = "C:\Program Files\Jenkins\jenkins.exe"
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
    }
}

