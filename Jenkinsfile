pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                script {
                    echo "Testing the application..."
                    echo "Executing pipeline for branch ${BRANCH_NAME}" 
                }
            }
        }

        stage("Build") {
    steps {
        script {
            def branch = env.BRANCH_NAME ?: 'unknown'
            echo "Building branch ${branch}"
        }
    }
}


        stage('Deploy') {
            when {
                expression { BRANCH_NAME == 'main' }
            }
            steps {
                script {
                    echo "Deploying the application..."
                }
            }
        }
    }
}
