FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN ./gradlew build
#COPY ./build/libs/*.jar app.jar
COPY . .
EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-jar","./build/libs/food-services-recipes-0.0.1-SNAPSHOT.jar"]