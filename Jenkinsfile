pipeline {
    agent any

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'Branch name')
    }

    stages {
        stage('Test') {
            steps {
                script {
                    echo "Testing the application..."
                    echo "Executing pipeline for branch ${params.BRANCH_NAME}"
                }
            }
        }

        stage('Build') {
            when {
                expression { params.BRANCH_NAME == 'master' }
            }
            steps {
                script {
                    echo "Building the application..."
                }
            }
        }

        stage('Deploy') {
            when {
                expression { params.BRANCH_NAME == 'master' }
            }
            steps {
                script {
                    echo "Deploying the application..."
                }
            }
        }
    }
}
