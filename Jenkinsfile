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
        stage('Code Quality Check via SonarQube') {
   steps {
       script {
       def scannerHome = tool 'sonarqube';
           withSonarQubeEnv("sonarqube-container") {
           sh "${tool("sonarqube")}/bin/sonar-scanner \
           -Dsonar.projectKey=test-node-js \
           -Dsonar.sources=. \
           -Dsonar.css.node=. \
           -Dsonar.host.url=http://localhost/:9000 \
           -Dsonar.login=your-generated-token-from-sonarqube-container"
               }
           }
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
