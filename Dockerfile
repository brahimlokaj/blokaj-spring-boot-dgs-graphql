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
