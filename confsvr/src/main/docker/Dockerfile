FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd && apk add curl
RUN mkdir -p /usr/local/configservice
WORKDIR /usr/local/configservice
ADD @project.build.finalName@.jar .
ADD wait-for.sh .
RUN chmod +x wait-for.sh
ENTRYPOINT ["java -jar @project.build.finalName@.jar"
