@Library('jenkins-shared-pipeline')_

pipeline {
    agent any
    environment {
        ECR_URL = '778179562648.dkr.ecr.eu-west-1.amazonaws.com'
        CONTAINER_IMAGE = 'service.product'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'git submodule update --init'
                sh './gradlew clean'
                sh './gradlew build -x test'
                sh './gradlew installDist'
            }
        }
        stage('Unit & Integration Tests') {
                  steps {
                      script {
                          try {
                              sh 'docker-compose up -d'
                              sleep time: 1000, unit: 'MILLISECONDS'
                              sh './gradlew run --args="flyway migrate"'
                              sh '
                              '
                          } finally {
                              junit '**/build/test-results/test/*.xml' //make the junit test results available in any case (success & failure)
                          }
                      }
                  }
                  post {
                        always {
                            sh 'docker-compose down'
                        }
                  }
              }
        stage('Sanity Check') {
            steps {
                sh '''
                     ./gradlew jibDockerBuild -Djib.to.image=${CONTAINER_IMAGE}:check --console=plain
                        (
                            set +e
                            DURATION=30
                            timeout $DURATION docker-compose --file docker-compose-sanity.yml up
                            code=$?
                            set -e
                            [ $code -eq 124 ] && exit 0 || exit 1
                        )
                '''
            }
            post {
                always {
                    sh 'docker-compose --file docker-compose-sanity.yml down'
                    sh 'docker image rm ${CONTAINER_IMAGE}:check'
                }
            }
        }
        stage('Deploy') {
             when {
                 anyOf {
                     branch 'master';
                 }
             }
             steps {
                 sh './gradlew jib -Djib.to.image=${ECR_URL}/${CONTAINER_IMAGE}:${BUILD_ID} --console=plain'
             }
         }
    }
    post {
        always {
          sendNotification currentBuild.result
        }
      }
}
