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
                step([$class : 'Publisher', reportFilenamePattern : '**/testng-results.xml'])
            }

        }
    }
}