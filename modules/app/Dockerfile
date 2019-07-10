FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar financeiro-fluxocaixa-app.jar
ENTRYPOINT ["java","-jar","/financeiro-fluxocaixa-app.jar"]