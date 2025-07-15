FROM eclipse-temurin:21-jdk

WORKDIR /veciapp

# Copia tu JAR (asegurándote de que ya incluya lo necesario para OpenAI, si lo usas desde Java)
COPY target/veciapp-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/foodster.jar"]
