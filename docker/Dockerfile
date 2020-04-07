# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine
# copy WAR into image
COPY ukol05.war /app.war 
# run application with this command line 
CMD ["/usr/bin/java", "-jar", "/app.war"]

