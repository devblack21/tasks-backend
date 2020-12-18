pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
              bat 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
              bat 'mvn test'
            }
        }
        /* stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
             steps {
              withSonarQubeEnv('SONAR_LOCAL') {*/
                 //bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http:localhost:9000 -Dsonar.login=kdjkjffajkfjdkjfa8823hhh324 -Dsonar.java.binaries=targe -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
             /* }   
            }    
        } 
        stage ('Qualyte Gate') {
             steps {
                sleep(20)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true 
                }
            }    
        } */
        stage ('Deploy BackEnd') {
             steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }    
        }  
        stage ('API Test') {
             steps {
                 dir('api-test') {
                    git credentialsId: 'github_login', url: 'https://github.com/devblack21/task-api-test.git'
                    bat 'mvn test'
                 }
            }    
        }
        stage ('Deploy FrontEnd') {
             steps {
                 dir('frontend') {
                      git credentialsId: 'github_login', url: 'https://github.com/devblack21/tasks-frontend.git'
                      bat 'mvn clean package'
                      deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
                 }
            }    
        }
        /*stage ('Functional Test') {
             steps {
                 dir('functional-test') {
                    git credentialsId: 'github_login', url: 'https://github.com/devblack21/task-functional-test.git'
                    bat 'mvn test'
                 }
            }    
        }*/

        stage ('Deploy Prod') {
             steps {
                bat 'docker-compose build'
                bat 'docker-compose up --force-recreate -d'
            }    
        }

        

    }
}

