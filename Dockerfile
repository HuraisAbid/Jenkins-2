FROM eclipse-temurin:17-jdk
COPY target/todo-app-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
