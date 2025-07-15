FROM eclipse-temurin:21

WORKDIR /veciapp

# Copia tu JAR al contenedor
COPY target/veciapp-0.0.1-SNAPSHOT.jar /veciapp/app/veciapp.jar


EXPOSE 8080

# Ejecuta el JAR
CMD ["java", "-jar", "/app/veciapp.jar"]
