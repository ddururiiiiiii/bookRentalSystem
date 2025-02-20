# 1️⃣ OpenJDK 17을 기반으로 사용
FROM openjdk:17

# 2️⃣ 작업 디렉토리 설정
WORKDIR /app

# 3️⃣ JAR 파일을 컨테이너에 복사 (Maven으로 빌드된 결과물)
COPY target/*.jar app.jar

# 4️⃣ 컨테이너에서 사용할 포트 설정 (Render는 8080 기본 사용)
EXPOSE 8080

# 5️⃣ 애플리케이션 실행 (JAR 파일 실행)
CMD ["java", "-jar", "app.jar"]
