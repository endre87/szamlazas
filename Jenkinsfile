#!/usr/bin/env groovy

pipeline {
    agent { docker 'maven:3.3.3' }

    stages {
        stage('build') {
            steps {
                // sh 'mvn --version'
                sh 'pwd'
                // sh 'mvn package -Dmaven.test.skip=true'
            }
        }

        // stage('deploy') {
        //     steps {
        //
        //     }
        // }
    }
}
