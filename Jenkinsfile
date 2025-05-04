pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    echo "Building the Docker image..."
                    sh 'docker build -t my-image .'
                }
            }
        }

        stage('Login to DockerHub') {
            steps {
                script {
                    echo "Logging in to DockerHub..."
                    sh 'echo "$PASS" | docker login -u "$USER" --password-stdin'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    echo "Pushing the Docker image to registry..."
                    sh 'docker push my-image'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo "Deploying the application..."
                    // Tambahkan perintah deploy Anda di sini
                }
            }
        }
    }
}
