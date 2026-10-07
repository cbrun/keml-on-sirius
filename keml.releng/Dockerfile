# Build the complete Maven reactor before building this runtime image.
# The context is ../keml.app, so only the packaged application is copied.
FROM eclipse-temurin:21-jre
# Apache POI measures fonts when sizing the analysis workbooks, even in headless mode.
RUN apt-get update && apt-get install -y --no-install-recommends fontconfig fonts-dejavu-core \
    && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY target/keml.jar app.jar
EXPOSE 8080
# A numeric user avoids running the server as root. Java's temporary files use /tmp.
USER 10001
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
