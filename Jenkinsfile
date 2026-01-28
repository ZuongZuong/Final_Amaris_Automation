pipeline {
    agent any

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select browser for execution')
        string(name: 'URL', defaultValue: 'https://www.iberia.com/vn/', description: 'Base URL for the application')
        choice(name: 'HEADLESS', choices: ['true', 'false'], description: 'Run tests in headless mode')
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    echo "Starting automation execution on ${params.BROWSER} browser"
                    echo "Target URL: ${params.URL}"
                    echo "Headless mode: ${params.HEADLESS}"
                    
                    // Display Maven version to verify it's on the PATH
                    sh 'mvn -version'
                }
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
    }

    post {
        always {
            script {
                // The 'allure' step requires the 'Allure Jenkins Plugin' to be installed.
                // If it's not installed, this block might still fail with NoSuchMethodError.
                // Ensure the plugin is installed in Jenkins -> Manage Jenkins -> Plugins.
                try {
                    allure includeProperties: false, 
                           jdk: '', 
                           results: [[path: 'target/allure-results']]
                } catch (Exception e) {
                    echo "Could not generate Allure report. Please ensure 'Allure Jenkins Plugin' is installed."
                    echo "Error: ${e.message}"
                }
            }
        }
        
        success {
            echo 'Automation Test successfully completed!'
        }
        
        failure {
            echo 'Automation Test failed. Please check the console logs.'
        }

        cleanup {
            cleanWs()
        }
    }
}
