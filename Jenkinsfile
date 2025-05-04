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
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    echo "Building Docker image..."
                    withCredentials([usernamePassword(credentialsId: '9946e4cd-bb94-427b-a87c-45a5596a0d93', usernameVariable: 'USER', passwordVariable: 'PASS')]) {

                        echo "Checking Docker installation..."
                        sh 'docker --version'  // Check if Docker is installed

                        echo "Logging in to DockerHub..."
                        sh 'echo "$PASS" | docker login -u "$USER" --password-stdin'

                        echo "Building Docker image..."
                        sh 'docker build -t yourdockerhubusername/your-image-name:latest .'

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
