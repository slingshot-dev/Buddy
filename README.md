# Buddy

Projet 6 : PayMyBuddy
Application de Transfert d'argent entre amis.


A Spring Boot App to expose API Rest.
All the URL, expose informations or performs modidication from/in the BDD (MySQL) 


Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

Prerequisites
What things you need to install the software and how to install them

Java 1.8
Maven 3.6.3
Spring Boot 2.2.6
MySQL Server

Installing
A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

3.Create SpringBooot Project

https://start.spring.io/
https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started-installing-spring-boot


Running App

java -jar PayMyBuddy-0.0.1.jar


Testing

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.


mvn test


To generate Surefire Report

mvn surefire-report:report


To generate Reports (ex Jacoco)

mvn site
