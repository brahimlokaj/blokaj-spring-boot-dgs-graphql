# Spring Boot with Netflix DGS Framework 
###### By Brahim Lokaj
![](https://img.shields.io/badge/build-success-brightgreen.svg)

### Read and learn about GraphQL https://graphql.org/learn/

# Stack
![](https://img.shields.io/badge/java_17-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot_2.7.6-✓-blue.svg)
![](https://img.shields.io/badge/netflix_dgs_4.9.25-✓-blue.svg)
![](https://img.shields.io/badge/mapstruct_1.5.2-✓-blue.svg)

# File structure
```
blokaj-spring-boot-dgs-graphql/
│
├── src/main/java/org/blokaj/graphql
│   ├── data
│   │   └── PersonData.java
│   │
│   ├── fetchers
│   │   └── PersonFetcher.java
│   │
│   ├── mappers
│   │   └── PersonMapper.java
│   │
│   ├── service
│   │   ├── impl
│   │   │   └── PersonServiceImpl.java
│   │   └── PersonService.java
│   │
│   └── BlokajSpringSecurityApplication.java
│    
├── src/main/resources/
│   ├── schema
│   │   └── errors.graphql
│   │   └── inputs.graphql
│   │   └── schema.graphql
│   │   └── types.graphql
│   │   └── unions.graphql
│   │
│   └── application.yml
│
├── src/test/java/
│   └── org/blokaj/graphql
│       ├── schema
│       │   └── GraphQLQueryRequestBuilder.java
│       │   └── MockData.java
│       │   
│       └── PersonFetcherTest.java
│   
├── .gitignore
├── build.gradle
├── docker-compose.yaml
├── Dockerfile
├── gradlew
├── gradlew.bat
├── LICENSE
├── README.md
└── settings.gradle
```

# Netflix DGS Framework
#### Official: (https://netflix.github.io/dgs)
#### Read documentation: (https://netflix.github.io/dgs/getting-started/)

# Configuration Details
```properties
server.port=8081
```
# Dockerfile Configuration
```dockerfile
FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/apps/spring-boot-dgs-graphql/src
WORKDIR /home/apps/spring-boot-dgs-graphql/src
RUN gradle build --no-daemon

#
# Package stage
#
FROM openjdk:17-jdk-slim-buster
WORKDIR /opt/apps
COPY --from=build /home/apps/spring-boot-dgs-graphql/src/build/libs/blokaj-spring-boot-dgs-graphql-1.0.0-SNAPSHOT.jar spring-boot-dgs-graphql.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","spring-boot-dgs-graphql.jar"]
```

# Docker Compose Configuration
```yml
version: '3'

services:
  ### BLokaj Spring Security
  blokaj-spring-boot-dgs-graphql:
    container_name: blokaj-spring-boot-dgs-graphql
    build:
#      context:  #the path must be where the app has been located in your local machine.
      dockerfile: Dockerfile #How is the dockerfile name
    ports:
      - '8081:8081' #in which port the app will be
    networks:
      - blokaj

networks:
  blokaj:
```

# How to use this code?
1. Make sure you have [Java 17](https://oracle.com/java/technologies/javase/jdk17-archive-downloads.html) and [Gradle](https://gradle.org/install/) installed
2. Fork this repository and clone it
```
$ git clone git@github.com:brahimlokaj/blokaj-spring-boot-dgs-graphql.git
```
3. Navigate into the folder
```
$ cd blokaj-spring-boot-dgs-graphql
```
4. Install dependencies
```
$ gradle clean build
```
5. Run the project
```
$ gradle bootRun
```
6. Navigate to `http://localhost:8081/graphiql` in your browser to check everything is working correctly. You can change the default port in the `application.properties` file
```properties
server.port=8080
```
Reed documentation how to call GraphQL APIs from postman (https://learning.postman.com/docs/sending-requests/graphql/graphql/ or https://www.baeldung.com/graphql-postman)
7. Call Query and Mutation

Mutation:
```graphql
mutation{
    AddPerson(input:{
        age: 35
        firstName: "Brahim"
        lastName: "Lokaj"
        email: "blokaj@example.org"
        phoneNumber: "+38345100001"
    }) {
        __typename
        ... on PersonInfoType {
            id
            firstName
            lastName
            age
            email
            phoneNumber
        }
        ... on GeneralError {
            code
            message
        }
    }
}
```
The response will look like
```json
{
  "data": {
    "AddPerson": {
      "__typename": "PersonInfoType",
      "id": "d9113dc2-1a94-4ab5-bae2-f2f3f4bc6011",
      "firstName": "Brahim",
      "lastName": "Lokaj",
      "age": 35,
      "email": "blokaj@example.org",
      "phoneNumber": "+38345100001"
    }
  }
}
```

Query:

```graphql
query {
    FindPersons(where: {
        email: "blokaj@example.org"
        phoneNumber: "+38345100001"
    }) {
    __typename
    ... on InformationOfPersonsResponse {
        persons {
            id
            firstName
            lastName
            age
            email
            phoneNumber
        }
    }
    ... on GeneralError {
        code
        message
    }
  }
}
```
The response will look like
```json
{
  "data": {
    "FindPersons": {
      "__typename": "InformationOfPersonsResponse",
      "persons": [
        {
          "id": "d9113dc2-1a94-4ab5-bae2-f2f3f4bc6011",
          "firstName": "Brahim",
          "lastName": "Lokaj",
          "age": 35,
          "email": "blokaj@example.org",
          "phoneNumber": "+38345100001"
        }
      ]
    }
  }
}
```

# Run Application With Docker
1. Run

```$ docker compse build ```

2. Run

```$ docker compse up ```