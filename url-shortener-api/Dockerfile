FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/url-shortener-api-0.0.1-SNAPSHOT.jar url-shortener-app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar url-shortener-api.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar urlshortenerassignment.jar
