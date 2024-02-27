# Use an official OpenJDK runtime as the base image
FROM adoptopenjdk:11-jdk-hotspot

# Install necessary packages including unzip
RUN apt-get update && apt-get install -y unzip

# Install Chrome
RUN apt-get update && apt-get install -y wget gnupg
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list
RUN apt-get update && apt-get install -y google-chrome-stable

# Install ChromeDriver
RUN wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/98.0.4758.102/chromedriver_linux64.zip
RUN unzip /tmp/chromedriver.zip -d /usr/bin/
RUN chmod +x /usr/bin/chromedriver

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/webscrapper-0.0.1-SNAPSHOT.jar /app/webscrapper-0.0.1-SNAPSHOT.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Set the entrypoint command to run the JAR file
ENTRYPOINT ["java", "-jar", "webscrapper-0.0.1-SNAPSHOT.jar"]