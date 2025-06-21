pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Book Positive Tests') {
            steps {
                script {
                    runCommand('mvn clean test -Dtest=BookHappyPathTest', 'mvn clean test -Dtest=BookHappyPathTest')
                }
            }
        }

        stage('Book Negative Tests') {
            steps {
                script {
                    runCommand('mvn clean test -Dtest=BookNegativeTest', 'mvn clean test -Dtest=BookNegativeTest')
                }
            }
        }

        stage('Book Edge Case Tests') {
            steps {
                script {
                    runCommand('mvn clean test -Dtest=BookEdgeCaseTest', 'mvn clean test -Dtest=BookEdgeCaseTest')
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'src/test/html_logs/**/*', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}

def runCommand(String linuxCommand, String windowsCommand) {
    if (isUnix()) {
        sh linuxCommand
    } else {
        bat windowsCommand
    }
}