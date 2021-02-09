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
        stage("Email"){
        			steps{
        				emailext (to: 'pramodrao81@gmail.com', replyTo: 'pramodrao81@gmail.com', subject: "Email Report from - '${env.JOB_NAME}' ", body: readFile("target/surefire-reports/emailable-report.html"), mimeType: 'text/html');
        			}
        		}
    }
}