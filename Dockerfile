FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY . .
RUN ./gradlew build
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]