pipeline {
    agent any

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select browser for execution')
        string(name: 'URL', defaultValue: 'https://www.iberia.com/vn/', description: 'Base URL for the application')
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
                sh """
                    mvn clean test \
                    -DsuiteXmlFile=regression.xml \
                    -DBROWSER=${params.BROWSER} \
                    -DURL=${params.URL} \
                    -DHEADLESS=${params.HEADLESS}
                """
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        // Ensure coverage report is generated and then run analysis
                        sh "mvn jacoco:report sonar:sonar"
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // This will wait for SonarQube to finish processing and return the result
                    // Note: This requires a Webhook to be configured in SonarQube (pointing to Jenkins)
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

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