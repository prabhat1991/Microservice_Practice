FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/eurekaservice
WORKDIR /usr/local/eurekaservice
ADD @project.build.finalName@.jar .
ADD wait-for.sh .
RUN chmod +x wait-for.sh
ENTRYPOINT ["java -jar @project.build.finalName@.jar"
