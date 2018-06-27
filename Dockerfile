FROM azul/zulu-openjdk:8
COPY target/microservice4-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/app.jar"]
