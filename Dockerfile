FROM openjdk:11

ADD target/cnc-contact-time.jar cnc-contact-time.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "cnc-contact-time.jar"]