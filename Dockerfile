FROM openjdk:11-jdk
#FROM alpine
#ADD target/olnester-spring-boot-app.jar olnester-spring-boot-app.jar
ADD target/game game
ADD target/run.sh run.sh

EXPOSE 8080 8888
ENTRYPOINT ["/bin/sh", "game"]
#ENTRYPOINT ["/bin/sh", "./run.sh"]