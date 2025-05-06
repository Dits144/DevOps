pipeline {
    agent any

    tools {
        maven 'maven-3.9'
    }

    stages {
        stage('Build jar') {
            steps {
                script {
                    echo "Building the jar file..."
                    try {
                        sh 'mvn clean package'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error("Build failed: ${e.message}")
                    }
                }
            }
        }

        stage('Build Image') {
            steps {
                script {
                    echo "Building the Docker image..."
                    try {
                        withCredentials([usernamePassword(credentialsId: '9946e4cd-bb94-427b-a87c-45a5596a0d93', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                            sh 'docker build -t muha22301/demo-app:jma-2.0 .'
                            sh "echo \$PASS | docker login -u \$USER --password-stdin"
                            sh 'docker push muha22301/demo-app:jma-2.0'
                        }
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error("Docker build or push failed: ${e.message}")
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo "Deploying the application..."
                    // Add your deploy logic here
                    // Example: sh 'kubectl apply -f deployment.yaml'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for details.'
        }
    }
}
