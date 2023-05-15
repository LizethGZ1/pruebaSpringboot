pipeline {
    agent any
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
        stage('Maven Install') {
    	agent {
      	docker {
        	image 'maven:3.5.0'
        }
      }
      steps {
      	sh 'mvn clean install'
      }
    }
        stage ("Probar si funciona Docker") {
            steps {
                sh "docker version"
            }
        }
        stage ("Docker build") {
            steps {
                script{
                sh "docker build -f pruebaSpringboot/Dockerfile -t lgonzalezz/prueba-repo:1.0.0-${BUILD_ID} pruebaSpringboot"
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
        stage ("Deploy to staging") {
            steps {
                sh "docker run -d --rm -p 8765:8080 --name calculatorStaging juanmamacgyvercode/calculator"
            }
        }
        // Si quieres que Jenkins haga un rest de aceptación de una repo docker, no uses localhost, usa tu IP.
        /*stage ("Prueba") {
                    steps {
                        sleep 20
                        sh "curl -i \"192.168.2.89:8765/sum?a=1&b=2\""
                    }
                }*/
        stage ("Acceptance test") {
            steps {
                sleep 60
                sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"

            }
            post {
                always {
                    sh "docker stop calculatorStaging"
                }
            }
        }
    }
}
