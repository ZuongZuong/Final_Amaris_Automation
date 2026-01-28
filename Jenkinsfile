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

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Execute Tests') {
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
    }

    post {
        always {
            allure results: [[path: 'target/allure-results']]
        }

        success {
            echo '✅ Automation Test successfully completed!'
        }

        failure {
            echo '❌ Automation Test failed. Please check Allure report and logs.'
        }

        cleanup {
            cleanWs()
        }
    }
}
