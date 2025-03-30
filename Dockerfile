# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file from the target folder into the container
COPY target/crudapp-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 so that the application is accessible
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
