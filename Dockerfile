FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /kheir
COPY build/libs/kheir-0.0.1.jar kheir.jar

CMD ["java","-jar","kheir.jar"]

