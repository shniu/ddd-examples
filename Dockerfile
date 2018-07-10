FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} july.jar
ARG APPLICATION_PROP
ADD ${APPLICATION_PROP} application.properties
EXPOSE 7099
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/july.jar","--spring.profiles.active=prod"]