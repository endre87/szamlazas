#!/usr/bin/env groovy

pipeline {
    agent { docker 'maven:3.3.3' }

    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
                sh 'pwd'
                sh 'mvn package -Dmaven.test.skip=true'
            }
        }

        stage('Deploy') {
            steps {
                sh 'rm -frv /var/lib/jenkins/deploy/**'
                sh 'mkdir -p /var/lib/jenkins/deploy/target'
                sh 'cp -v cmdline/target/cmdline-1.0.0-jar-with-dependencies.jar /var/lib/jenkins/deploy/target/'
                sh 'cp -v cmdline/GenerateInvoiceXls.sh /var/lib/jenkins/deploy/'
            }
        }

        stage('Sanity check') {
            steps {
                input "Does the staging environment look ok?"
            }
        }

        // stage('deploy') {
        //     steps {
        //
        //     }
        // }
    }
}
