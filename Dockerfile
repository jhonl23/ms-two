FROM openjdk
ADD ./target/ms-two-0.0.1-SNAPSHOT.jar mstwo.jar
ENTRYPOINT ["java", "-jar", "mstwo.jar"]