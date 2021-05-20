
#FROM  maven:3.6-openjdk-8
#USER root
#WORKDIR /movie-service
#COPY . .
#RUN mvn package
#
#FROM openjdk:8-jdk-alpine
#WORKDIR /movie-service
#ARG JAR_FILE=/movie-service/target/*.jar
#COPY --from=0 ${JAR_FILE} movie.jar
#ENTRYPOINT ["java","-jar","movie.jar"]

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} movie.jar
ENTRYPOINT ["java","-jar","/movie.jar"]