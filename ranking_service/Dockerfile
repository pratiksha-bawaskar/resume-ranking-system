FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

# build jar
RUN ./mvnw clean package -DskipTests

# check jar (debug)
RUN ls target

EXPOSE 8080

# auto detect jar
CMD ["sh", "-c", "java -jar target/*.jar"]