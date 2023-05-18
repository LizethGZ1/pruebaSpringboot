pipeline {
    agent any
    tools{
        maven 'MavenTool'
        // a bit ugly because there is no `@Symbol` annotation for the DockerTool
        // see the discussion about this in PR 77 and PR 52: 
        // https://github.com/jenkinsci/docker-commons-plugin/pull/77#discussion_r280910822
        // https://github.com/jenkinsci/docker-commons-plugin/pull/52
        'org.jenkinsci.plugins.docker.commons.tools.DockerTool' '18.09'
    }
    environment {
    DOCKER_CERT_PATH = credentials('DockerTool')
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
                script{
                    withDockerRegistry(credentialsId: 'dockerHub') {
                    sh "docker version"
                    }
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
