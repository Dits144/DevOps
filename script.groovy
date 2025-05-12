def buildJar() {
    echo 'Building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: '9946e4cd-bb94-427b-a87c-45a5596a0d93', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t muha22301/demo-app:jma-2.0 .'
        sh "echo \$PASS | docker login -u \$USER --password-stdin"
        sh 'docker push muha22301/demo-app:jma-2.0'
    }
}

def deployApp() {
    echo 'Deploying the application...'
}

return this
