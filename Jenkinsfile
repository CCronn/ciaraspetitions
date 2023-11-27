pipeline {
    agent any

    stages {
        stage('Get Code') {
            steps {
                // Get code from my GitHub repo
                git 'https://github.com/CCronn/ciaraspetitions.git'
            }
        }

        stage('Build') {
            steps {
                // Build it
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Test it
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                // Package
                sh 'mvn package'
                archiveArtifacts artifacts: 'target/ciaraspetitions.war', fingerprint: true
            }
        }

        stage('Build Docker') {
            steps {
                sh 'docker build -f Dockerfile -t ciaraspetitions:latest .'
            }
        }

        stage('Run Docker') {
            steps {
                sh 'docker run -d -p 9090:8080 ciaraspetitions'
            }
        }

        stage('Manual Approval') {
            steps {
                // Integrate manual approval step
                input 'Do you want to proceed with deployment?'
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
