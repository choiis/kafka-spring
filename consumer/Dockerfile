FROM openjdk:11
EXPOSE 18080 18085
COPY consumer.jar /opt/consumer.jar
RUN apt-get update && apt-get upgrade -y && apt-get install curl telnet vim net-tools -y && chmod 755 /opt/consumer.jar
ENTRYPOINT ["/opt/consumer.jar"]