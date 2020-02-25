# Event Sourcing and CQRS Examples

## Introduction
This is a forked project of https://github.com/andreschaffer/event-sourcing-cqrs-examples in order to show some sample API tests that I have written against the original repository which uses CQRS and Event Sourcing.

## Trying it out
### Pre-Requisites
 + [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
 + [Maven](http://maven.apache.org/download.cgi)
 
### Packaging the JAR
`mvn package`

### Starting the application
` java -jar target/bank-service-1.0-SNAPSHOT.jar server src/environments/development.yml `

### Running the integration tests
`mvn clean verify`