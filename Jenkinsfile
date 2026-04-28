pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Build') {
            steps {
                bat '"C:\\Windows\\System32\\cmd.exe" /c "C:\\ProgramData\\Jenkins\\.jenkins\\tools\\hudson.tasks.Maven_MavenInstallation\\Maven3\\bin\\mvn.cmd clean install"'
            }
        }

        stage('Test') {
            steps {
                bat '"C:\\Windows\\System32\\cmd.exe" /c "C:\\ProgramData\\Jenkins\\.jenkins\\tools\\hudson.tasks.Maven_MavenInstallation\\Maven3\\bin\\mvn.cmd test"'
            }
        }
    }
}
