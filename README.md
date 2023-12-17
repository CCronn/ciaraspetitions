# Ciaras Petitions


###### Purpose
A project build for the CT5171 module (Cloud DevOps) in the University of Galway 2023.

###### What is it?
This a web application built using SpringBoot. It is a website for petitions which allows users to submit or sign petitions, in addition to viewing and searching for petitions. 

###### How to use
This project uses the [Jenkinsfile](https://github.com/CCronn/ciaraspetitions/blob/master/Jenkinsfile) to run the application in a docker container on an ubuntu Virtual machine. 
If you would like to try, ensure that [jenkins is installed](https://www.jenkins.io/doc/book/installing/linux/) on your machine, and [create a new pipeline](https://www.lambdatest.com/blog/jenkins-declarative-pipeline-examples/) using the [Jenkinsfile](https://github.com/CCronn/ciaraspetitions/blob/master/Jenkinsfile) as your declarative script. When the pipeline finishes successfully, the application should run locally at:

http://localhost:9090/ciaraspetitions/

