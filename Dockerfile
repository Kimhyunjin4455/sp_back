FROM openjdk:17

COPY build/libs/starbucksServer-0.0.1-SNAPSHOT.jar starbucksServer.jar

ENTRYPOINT ["java", "-jar", "starbucksServer.jar"]
