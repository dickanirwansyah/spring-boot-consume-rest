FROM openjdk:8
ADD target/demo-producer.jar demo-producer.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "demo-producer.jar"]