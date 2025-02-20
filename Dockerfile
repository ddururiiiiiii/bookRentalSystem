# 1️⃣ Gradle과 OpenJDK 17을 포함한 베이스 이미지 사용
FROM gradle:8.4-jdk17 AS build

# 2️⃣ 작업 디렉토리 설정
WORKDIR /app

# 3️⃣ 프로젝트 코드 복사
COPY --chown=gradle:gradle . .

# 4️⃣ Gradle 빌드 실행 (테스트 생략)
RUN gradle build -x test

# 5️⃣ 실행용 이미지로 전환 (Eclipse Temurin JDK 17 사용)
FROM eclipse-temurin:17
WORKDIR /app

# 6️⃣ 빌드된 JAR 파일을 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 7️⃣ 포트 설정
EXPOSE 8080

# 8️⃣ JAR 실행
CMD ["java", "-jar", "app.jar"]
