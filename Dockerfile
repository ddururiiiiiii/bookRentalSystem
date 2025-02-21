# 기존 Dockerfile 수정
FROM gradle:8.4-jdk17 AS builder
WORKDIR /app

# 프로젝트 소스 코드 복사
COPY . .

# Gradle 빌드 실행 (JAR 파일 생성)
RUN ./gradlew bootJar

# 실행할 Spring Boot JAR 파일을 지정
FROM openjdk:17
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]
