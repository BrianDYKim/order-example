FROM openjdk:11-jdk

COPY . /order-example

WORKDIR /order-example

CMD ["./gradlew", "bootRun"]