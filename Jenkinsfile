pipeline {
    agent any

    tools {
        maven 'maven-3.9'
    }

    stages {
        stage('Build jar') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Build Image') {
            steps {
                script {
                    echo "Building the Docker image..."
                    withCredentials([usernamePassword(credentialsId: '9946e4cd-bb94-427b-a87c-45a5596a0d93', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t muha22301/demo-app:jma-2.0 .'
                        sh "echo \$PASS | docker login -u \$USER --password-stdin"
                        sh 'docker push muha22301/demo-app:jma-2.0'
                    }
                    
                    // Notifikasi ke Discord setelah build image
                    def webhookUrl = 'https://discordapp.com/api/webhooks/1371549307827781763/7cGxC2z6Ep0UfUklstlzQhXN6c07CRDobkVBblFxbiSdqPS-OGURifUjuurvjrCdY4aa'
                    def jsonPayload = '{"content": "Docker image built and pushed successfully: muha22301/demo-app:jma-2.0"}'
                    sh "curl -X POST -H 'Content-Type: application/json' -d '${jsonPayload}' ${webhookUrl}"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo "Deploying the application..."
                    
                    // Notifikasi ke Discord setelah deploy
                    def webhookUrl = 'https://discordapp.com/api/webhooks/1371549307827781763/7cGxC2z6Ep0UfUklstlzQhXN6c07CRDobkVBblFxbiSdqPS-OGURifUjuurvjrCdY4aa'
                    def jsonPayload = '{"content": "Application deployed successfully."}'
                    sh "curl -X POST -H 'Content-Type: application/json' -d '${jsonPayload}' ${webhookUrl}"
                }
            }
        }
    }
}
