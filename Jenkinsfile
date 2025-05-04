pipeline {
    agent any

    tools {
        maven 'maven-3.9'  // Pastikan tool Maven sudah terpasang dan dikonfigurasi di Jenkins
    }

    stages {
        stage('Build jar') {
            steps {
                script {
                    echo "Building JAR file..."
                    try {
                        // Pastikan build Maven berjalan dengan benar, menambahkan clean untuk menghindari masalah build
                        sh 'mvn clean package'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }

        stage('Build Image') {
            steps {
                script {
                    echo "Building Docker image..."

                    // Cek apakah Docker terpasang dan dapat dijalankan
                    try {
                        sh 'docker --version'
                    } catch (Exception e) {
                        error "Docker is not installed or not accessible on the Jenkins agent"
                    }

                    // Login ke DockerHub
                    withCredentials([usernamePassword(credentialsId: '9946e4cd-bb94-427b-a87c-45a5596a0d93', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                        echo "Logging in to DockerHub..."
                        try {
                            sh 'echo "$PASS" | docker login -u "$USER" --password-stdin'
                        } catch (Exception e) {
                            error "Docker login failed: ${e.getMessage()}"
                        }
                    }

                    // Build Docker image
                    try {
                        echo "Building Docker image..."
                        sh 'docker build -t muha22301/demo-app:jma-2.0 .'
                    } catch (Exception e) {
                        error "Docker image build failed: ${e.getMessage()}"
                    }

                    // Push Docker image
                    try {
                        echo "Pushing Docker image..."
                        sh 'docker push muha22301/demo-app:jma-2.0'
                    } catch (Exception e) {
                        error "Docker image push failed: ${e.getMessage()}"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo "Deploying the application..."

                    // Pastikan file deployment.yaml ada
                    try {
                        withCredentials([file(credentialsId: 'kube-config', variable: 'KUBE_CONFIG')]) {
                            // Pastikan kubeconfig berfungsi dan Kubernetes terkonfigurasi dengan benar
                            sh 'kubectl --kubeconfig=$KUBE_CONFIG apply -f deployment.yaml'
                        }
                    } catch (Exception e) {
                        error "Deployment failed: ${e.getMessage()}"
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
        always {
            echo 'Cleaning up...'
            // Pembersihan atau notifikasi bisa ditambahkan di sini
        }
    }
}
