pipeline {
    agent any
    tools{
        maven 'MavenTool'
        
    }
    
    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
                sh "echo _____________________________________"
            }
        }
        stage("Unit test") {
            steps {
                sh "./gradlew test"
                sh "echo _____________________________________"
            }
        }
        stage ("Package") {
            steps {
        	    sh "./gradlew build"
                sh "echo _____________________________________"
        	}
        }
        stage('SonarQube analysis') {
    withSonarQubeEnv(credentialsId: 'squ_29a59416786ea820dfae88b427fd383eb6980d8b', installationName: 'SonarQubeServer') { // You can override the credential to be used
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    }
  }
   stage("Install Project Dependencies") {
   steps {
       nodejs(nodeJSInstallationName: 'nodenv'){
           sh "npm install"
           }
       }
   }
        stage ("Docker login") {
            steps {
                script{
                    sh "docker login -u='lgonzalezz' -p='Iniesta06;'"
                    sh "echo _____________________________________"
                }
            }
        }
        stage ("Probar si funciona Docker") {
            steps {
                script{
                    withDockerRegistry(credentialsId: 'dockerHub') {
                    sh "docker version"
                    sh "echo _____________________________________"
                    }
                }
            }
        }
        stage ("Docker build") {
            steps {
                script{
                sh "docker build -t lgonzalezz/prueba-repo:1.0.0-${BUILD_ID} ."
                sh "echo _____________________________________"
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
