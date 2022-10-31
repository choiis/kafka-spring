FROM openjdk:11
EXPOSE 10100 10105
COPY reactor-producer.jar  /opt/reactor-producer.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y && chmod 755 /opt/reactor-producer.jar
ENTRYPOINT ["/opt/reactor-producer.jar"]