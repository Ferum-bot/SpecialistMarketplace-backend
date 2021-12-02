FROM gradle:7.3.0-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-alpine
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/spec-market-0.0.1.jar /app/
WORKDIR /app
EXPOSE 5000
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","spec-market-0.0.1.jar"]
