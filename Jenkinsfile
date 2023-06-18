pipeline {
    agent any
    environment {
        tag = sh(returnStdout: true, script: "git rev-parse --short=10 HEAD").trim()
    }

    stages {
        stage('compile') {
            steps {
                sh '/usr/local/Cellar/maven/3.8.4/libexec/bin/mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh '/usr/local/Cellar/maven/3.8.4/libexec/bin/mvn test'
            }
        }
        stage('package') {
            steps {
                sh '/usr/local/Cellar/maven/3.8.4/libexec/bin/mvn package'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh '/usr/local/bin/docker build -t mohammedaddoumi/all-review:develop-${tag} .'
                sh '/usr/local/bin/docker login -u mohammedaddoumi -p simoQB24188'
                sh '/usr/local/bin/docker push mohammedaddoumi/all-review:develop-${tag}'
            }
        }
        stage('Kubernetes Deployment') {
            steps {
                sh "sed -i 's|image: mohammedaddoumi/all-review:.*$|image: mohammedaddoumi/all-review:develop-${tag}|' kubernetes/deployment.yml"
                sh '/usr/local/bin/kubectl apply -f kubernetes/deployment.yml'
            }
        }

        stage('Kubernetes Service') {
            steps {
                sh '/usr/local/bin/kubectl apply -f kubernetes/service.yml'
            }
        }
    }
}