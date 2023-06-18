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
        stage('Modify File') {
            steps {
                script {
                    // Read the file content
                    def fileContent = readFile('kubernetes/deployment.yml')

                    // Replace the desired pattern in the content
                    def modifiedContent = fileContent.replaceAll('image: mohammedaddoumi/all-review:.*$', "image: mohammedaddoumi/all-review:develop-${tag}")

                    // Write the modified content back to the file
                    writeFile(file: 'kubernetes/deployment.yml', text: modifiedContent)
                }
            }
        }
        stage('Kubernetes Deployment') {
            steps {
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