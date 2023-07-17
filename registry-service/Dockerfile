FROM openjdk:18-jdk-alpine3.15
RUN apk --no-cache add curl
WORKDIR /app_service
COPY build/libs/*.jar /app_service/app.jar
RUN chmod +x app.jar .
CMD ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]