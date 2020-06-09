pipeline {

    triggers {pollSCM ''}

    parameters {
        choice(name: 'environment', choices: ['Live', 'Integration'], description: ''),
        string(name: 'tags', defaultValue: '', description: '')
    }

    stages {
        stage("Run") {
            steps {
                sh 'mvn clean test'
            }
        }
    }

}