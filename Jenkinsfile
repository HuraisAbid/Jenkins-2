pipeline {
    agent {
        docker {
            image 'docker:24.0.2-dind'   // Docker-in-Docker agent
            args '--privileged'          // Needed for Docker daemon
        }
    }

    environment {
        DOCKER_IMAGE = "todo-app-image"
    }

    stages {

        stage('Start Docker Daemon') {
            steps {
                sh '''
                    dockerd-entrypoint.sh > /tmp/dockerd.log 2>&1 &
                    sleep 5
                '''
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh '''
                    apk add maven openjdk17 --no-cache
                    mvn clean package -DskipTests=false
                '''
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Deploy Container') {
            steps {
                sh "docker rm -f todo-app || true"
                sh "docker run -d --name todo-app -p 8080:8080 ${DOCKER_IMAGE}"
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}
