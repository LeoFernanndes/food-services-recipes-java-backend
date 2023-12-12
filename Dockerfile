FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY . .
RUN ./gradlew build -x test # TODO: Fix deprecation error on building jar with tests
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]