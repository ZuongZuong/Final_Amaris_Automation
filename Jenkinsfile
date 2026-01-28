pipeline {
    agent any

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select browser for execution')
        string(name: 'URL', defaultValue: 'https://www.iberia.com/', description: 'Base URL for the application')
        choice(name: 'HEADLESS', choices: ['true', 'false'], description: 'Run tests in headless mode')
    }

    environment {
        MAVEN_HOME = tool 'Maven 3'
        PATH = "${env.MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    echo "Starting automation execution on ${params.BROWSER} browser"
                    echo "Target URL: ${params.URL}"
                    echo "Headless mode: ${params.HEADLESS}"
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Using 'verify' instead of 'test' to trigger the 80% JaCoCo coverage check
                sh """
                    mvn clean verify \
                    -DsuiteXmlFile=regression.xml \
                    -DBROWSER=${params.BROWSER} \
                    -DURL=${params.URL} \
                    -DHEADLESS=${params.HEADLESS}
                """
            }
        }

    //     stage('SonarQube Analysis') {
    //         steps {
    //             script {
    //                 try {
    //                     withSonarQubeEnv('SonarQube') {
    //                         sh "mvn jacoco:report sonar:sonar"
    //                     }
    //                 } catch (Exception e) {
    //                     echo "WARNING: SonarQube installation 'SonarQube' not found in Jenkins."
    //                     echo "Please configure it in Manage Jenkins -> System."
    //                     echo "Falling back to direct execution..."
    //                     sh "mvn jacoco:report sonar:sonar"
    //                 }
    //             }
    //         }
    //     }

    //     stage('Quality Gate') {
    //         steps {
    //             script {
    //                 try {
    //                     timeout(time: 1, unit: 'HOURS') {
    //                         waitForQualityGate abortPipeline: true
    //                     }
    //                 } catch (Exception e) {
    //                     echo "Quality Gate check skipped (requires SonarQube plugin configuration)."
    //                 }
    //             }
    //         }
    //     }
    // }

    post {
        always {
            script {
                allure includeProperties: false, 
                       jdk: '', 
                       results: [[path: 'target/allure-results']]
            }
        }
        
        success {
            echo 'Automation Test successfully completed!'
        }
        
        failure {
            echo 'Automation Test failed. Please check the Allure report and console logs.'
        }

        cleanup {
            cleanWs()
        }
    }
}