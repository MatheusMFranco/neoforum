FROM eclipse-temurin:21
EXPOSE 8080
ADD /target/neoforum-0.0.1-SNAPSHOT.jar neoforum.jar
ENTRYPOINT ["java", "$JAVA_OPTS -XX:+UseContainerSupport", "-Xmx300m -Xss512k -XX:CICompilerCount=2", "-Dserver.port=$PORT", "-Dspring.profiles.active=prod", "-jar", "neoforum.jar"]
