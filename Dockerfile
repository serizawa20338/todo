FROM maven:3-jdk-11

WORKDIR /build
COPY . /build

RUN mvn package -Dmaven.test.skip=true \
    && mv target/todo-*.jar target/application.jar

CMD ["java", "-jar", "target/application.jar"]
