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
                sh './gradlew build'
                sh './gradlew installDist'
            }
        }
        stage('Unit & Integration Tests') {
            steps {
                script {
                    try {
                        sh './gradlew clean test --no-daemon' //run a gradle task
                    } finally {
                        junit '**/build/test-results/test/*.xml' //make the junit test results available in any case (success & failure)
                    }
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
                        timeout $DURATION docker run --rm \
                            -e HTTP_PORT=8080 \
                            ${CONTAINER_IMAGE}:check 
                        code=$?
                        set -e
                        [ $code -eq 124 ] && exit 0 || exit 1
                    )
                '''
            }
            post {
                always {
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
