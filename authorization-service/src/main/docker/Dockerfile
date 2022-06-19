FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/authenticationservice
WORKDIR /usr/local/authenticationservice
ADD @project.build.finalName@.jar .
EXPOSE 9003
#ADD wait-for.sh .
#RUN chmod +x wait-for.sh
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait
#ENTRYPOINT ["java", "-jar", "@project.build.finalName@.jar"]