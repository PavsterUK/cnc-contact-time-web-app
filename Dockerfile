FROM openjdk:11

ADD target/Contact-time-calculator.jar Contact-time-calculator.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Contact-time-calculator.jar"]