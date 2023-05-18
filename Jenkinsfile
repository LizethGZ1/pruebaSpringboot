pipeline {
    agent any
    tools{
        maven 'MavenTool'
    }
    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage ("Package") {
            steps {
        	    sh "./gradlew build"
        	}
        }
        stage ("Probar si funciona Docker") {
            steps {
            withDockerRegistry(credentialsId: 'dockerHub') {
            sh "docker version"
            }
            }
        }
        stage ("Docker build") {
            steps {
                script{
                sh "docker build -t lgonzalezz/prueba-repo:1.0.0-${BUILD_ID} ."
                }
                
            }
        }
        stage ("Docker login") {
            steps {
                script{
                    sh "docker login -u='lgonzalezz' -p='Iniesta06;'"
                }
            }
        }
        stage ("Docker push") {
            steps {
                sh "docker push lgonzalezz/prueba-repo:1.0.0-${BUILD_ID}"
            }
        }
      }
}
