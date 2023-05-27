FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=javatgbotmmr_bot
ENV BOT_TOKEN=5762658092:AAEAYmfR6u2D8JYvkFfGQD9ZwzvO1-6lZ4Y
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]
