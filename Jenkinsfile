pipeline {
    agent any

    tools {
        maven 'maven-3.9'
    }
    
    stages {
        stage('Build jar') {
            steps {
                script {
                    echo "Building the Docker image..."
                    sh 'mvn package'
                }
            }
        }

        steps {
    script {
        echo 'Building Docker image...'
        sh 'docker build -t yourdockerhubusername/your-image-name:latest .'

        echo 'Logging in to DockerHub...'
        sh 'echo "$PASS" | docker login -u "$USER" --password-stdin'

        echo 'Pushing Docker image...'
        sh 'docker push yourdockerhubusername/your-image-name:latest'
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
