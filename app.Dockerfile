FROM openjdk:11
ADD target/insertions.jar insertions.jar

ENV WAIT_VERSION 2.7.3
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait
# Environment variables
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "insertions.jar"]