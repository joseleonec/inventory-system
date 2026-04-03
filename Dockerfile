# Etapa 1: Construcción
# Se utiliza una imagen JDK 25 para compilar y empaquetar la aplicación
FROM eclipse-temurin:25-jdk-jammy AS builder
WORKDIR /build

# Copiar archivos de configuración de Maven/Gradle primero para aprovechar el cache de capas
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
# Descargar dependencias (offline) para acelerar construcciones posteriores
RUN ./mvnw dependency:go-offline

# Copiar el código fuente y construir el JAR
COPY src src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Runtime
# Se utiliza una imagen JRE 25 (más ligera y segura) para la ejecución
FROM eclipse-temurin:25-jre-jammy
WORKDIR /app

# Crear un usuario no raíz por seguridad
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Copiar el JAR desde la etapa de construcción
# Ajusta el nombre del JAR según tu configuración en el pom.xml
COPY --from=builder /build/target/*.jar app.jar

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8080

# Configuración de la JVM para entornos de contenedores
ENTRYPOINT ["java", \
            "-XX:MaxRAMPercentage=75.0", \
            "-jar", \
            "app.jar"]
