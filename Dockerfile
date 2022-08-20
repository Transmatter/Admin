FROM openjdk:17-alpine
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir logs
VOLUME /logs
RUN apk add tzdata
EXPOSE 8080 8443

ENV JAVA_PROFILE dev-release
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java" ,   "-Dspring.profiles.active=${JAVA_PROFILE}",\
           "-cp","app:app/lib/*","transmatter.platform.administration.AdministrationApplication"]