pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Build') {
            steps {
                bat 'C:/Windows/System32/cmd.exe /c mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'C:/Windows/System32/cmd.exe /c mvn test'
            }
        }
    }
}
