pipeline {

    agent any   

    tools {
        maven "Maven-3.9"
    }

    environment {
        DOCKER_IMAGE = "todo-app-image"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh "mvn clean package -DskipTests=false"
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

        stage('Deploy') {
            steps {
                sh "docker rm -f todo-app || true"
                sh "docker run -d --name todo-app -p 9090:8080 ${DOCKER_IMAGE}"
            }
        }

    }
}
