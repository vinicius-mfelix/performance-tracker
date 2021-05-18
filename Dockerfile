FROM amazoncorretto:11

WORKDIR /app

COPY target/performance-tracker-0.0.1-SNAPSHOT.jar /app/performance-tracker.jar

EXPOSE 8080

CMD ["java", "-jar", "performance-tracker.jar"]