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
                    echo "Building Docker image..."
                    withCredentials([usernamePassword(credentialsId: '9946e4cd-bb94-427b-a87c-45a5596a0d93', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                        sh 'docker build -t muha22301/demo-app:jma-2.0 .'
                        sh "echo \$PASS | docker login -u \$USER --password-stadin"
                        sh 'docker push muha22301/demo-app:jma-2.0'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo "Deploying the application..."
                    }
                }
            }
        }
    }
