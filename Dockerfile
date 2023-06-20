FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=javatgbotmmr_bot
ENV BOT_TOKEN=5762658092:AAEAYmfR6u2D8JYvkFfGQD9ZwzvO1-6lZ4Y
ENV BOT_DB_USERNAME=jrtb_db_user
ENV BOT_DB_PASSWORD=jrtb_db_password
ENV BOT_RECOUNT_NEW_POSTS_RATE=900000
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-Dbot.recountNewPostsFixedRate=${BOT_RECOUNT_NEW_POSTS_RATE}", "-jar", "/app.jar"]
