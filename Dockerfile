FROM openjdk:11

ADD target/contact-time-webApp.jar contact-time-webApp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "contact-time-webApp.jar"]