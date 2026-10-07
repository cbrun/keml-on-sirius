FROM eclipse-temurin:21-jdk

RUN apt-get update && apt-get install -y --no-install-recommends \
	python3 \
	python3-pip \
	&& apt-get clean \
	&& rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/kemlanalysisserver-server.jar app.jar

COPY src/main/java/keml/analysis/py/requirements.txt ./python-scripts/requirements.txt
RUN pip3 install --no-cache-dir --break-system-packages -r python-scripts/requirements.txt

COPY src/main/java/keml/analysis/py/main.py ./python-scripts/main.py
COPY src/main/java/keml/analysis/py/workbook_analyser.py ./python-scripts/workbook_analyser.py
COPY src/main/java/keml/analysis/py/workbook_editor.py ./python-scripts/workbook_editor.py

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "DOCKER_JAR"]
