# 1️⃣ Maven을 포함한 OpenJDK 17 이미지를 사용 (빌드 가능)
FROM maven:3.8.6-openjdk-17 AS build

# 2️⃣ 작업 디렉토리 설정
WORKDIR /app

# 3️⃣ 프로젝트 코드 복사
COPY . .

# 4️⃣ Maven으로 JAR 파일 빌드
RUN mvn clean install -DskipTests

# 5️⃣ 실행용 이미지로 전환
FROM openjdk:17
WORKDIR /app

# 6️⃣ 빌드된 JAR 파일을 복사
COPY --from=build /app/target/*.jar app.jar

# 7️⃣ 포트 설정
EXPOSE 8080

# 8️⃣ JAR 실행
CMD ["java", "-jar", "app.jar"]
