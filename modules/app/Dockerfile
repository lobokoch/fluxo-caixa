FROM adoptopenjdk/openjdk8-openj9:latest
VOLUME /tmp
COPY target/*.jar financeiro-fluxocaixa-app.jar
ENTRYPOINT ["java", "-Xshareclasses:name=kerubin", "-jar", "/financeiro-fluxocaixa-app.jar"]