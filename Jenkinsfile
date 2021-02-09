pipeline{
    agent{
        node{
            label 'master'
        }
    }
    options{
        timestamps()
    }
    stages{
        stage("Checkout, Test and Publish"){
            steps{
                git 'https://github.com/pramodrao81/testrepo.git'
                script{
                    bat(/mvn clean test/)
                }
                publishHTML target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'test-output/LowLevel_Report',
                            reportFiles: 'Test-Automaton-Report.html',
                            reportName: 'Automation Report'
                          ]
            }

        }
    }
}