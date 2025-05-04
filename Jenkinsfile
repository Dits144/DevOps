pipeline {
    agent any

    tools {
        maven 'maven-3.9'
    }

    stages {
        stage('Build jar') {
            steps {
                script {
                    echo "Building the JAR file..."
                    sh 'mvn package'
                }
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    echo "Building Docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credential', passwordVariables: 'PASS', usernameVariable: 'USER')]) {

                    echo "Logging in to DockerHub..."
                    sh 'echo "$PASS" | docker login -u "$USER" --password-stdin'

                    echo "Pushing Docker image..."
                    sh 'docker push yourdockerhubusername/your-image-name:latest'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo "Deploying the application..."
                    // Tambahkan perintah deploy jika ada
                }
            }
        }
    }
}
