FROM openjdk:17-jdk-alpine

# create user to run app as (instead of root)
RUN addgroup -S app && adduser -S app -G app

# use user "app"
USER app

# copy the jar file into the docker image
COPY target/*.jar app.jar

# run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]