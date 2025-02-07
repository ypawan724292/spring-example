# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /imageProcessing

# Copy the build output to the container
COPY build/libs/imageProcessing-0.0.1.jar imageProcessing.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "imageProcessing.jar"]