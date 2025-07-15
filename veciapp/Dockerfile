FROM eclipse-temurin:21

# Establece el directorio de trabajo
WORKDIR /veciapp

# Crea el directorio 'app' dentro de '/veciapp'
RUN mkdir -p /veciapp/app

# Copia tu JAR al contenedor
COPY target/veciapp-0.0.1-SNAPSHOT.jar /veciapp/app/veciapp.jar

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta el JAR
CMD ["java", "-jar", "/veciapp/app/veciapp.jar"]
