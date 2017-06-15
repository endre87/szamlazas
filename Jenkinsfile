#!/usr/bin/env groovy

pipeline {
    agent none

    stages {
        stage('build') {
            agent {
                docker {
                    image 'mymaven:latest'
                    args  '-v /mnt/nfs:/mnt/nfs'
                }
            }
            steps {
                sh 'mvn --version'
                sh 'pwd'
                sh 'mvn package -Dmaven.test.skip=true'
            }
        }

        stage('deploy') {
            agent any
            steps {
                sh 'rm -frv /mnt/nfs/deploy/**'
                sh 'mkdir -p /mnt/nfs/deploy/target'
                sh 'cp -v cmdline/target/cmdline-1.0.0-jar-with-dependencies.jar /mnt/nfs/deploy/target/'
                sh 'cp -v cmdline/GenerateInvoiceXls.sh /mnt/nfs/deploy/'
            }
        }

        stage('sanity check') {
            agent any
            steps {
                input "Does the staging environment look ok?"
            }
        }
    }
}
