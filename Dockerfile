FROM openjdk:8-jre
ADD target/my-fat.jar app.jar
COPY build/libs/calculator-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
