# 팀내 도서 대여 시스템
- Book Rental System은 사용자들이 책을 등록하고, 대여 및 반납을 관리할 수 있는 웹 애플리케이션입니다.
- 이 시스템을 통해 도서를 편리하게 공유하고 관리할 수 있습니다.

----

## [개발 계기]
- 좋은 책들을 팀원들과 공유하고 싶었다. 그런데 구두로 빌리는 거 말고 개발자 답게 작은 웹 애플리케이션으로 이를 체계적으로 관리하고 싶었다. 그래서 시작한 프로젝트.

----

## [관심사 및 목표]
- 내가 가장 익숙하게 사용하는 기술로 빠르게 완성해보자.
- 팀원들이 실 사용할 수 있도록 하자.


----

## ✨ 주요 기능

### 🔐 회원 관리
- 회원가입 및 로그인 (Interceptor)
- 내 정보 수정

### 📚 도서 관리
- 도서 등록 / 수정 / 삭제
- 도서 검색 (책 제목, 저자, 출판사)
- 대여 가능 도서만 필터링

### 📖 대여 및 반납
- 책 대여 및 반납 기능
- 대여 기록 조회 (반납하지 않은 책만 보기)
- 대여 가능 여부 실시간 확인
----

## 🛠 기술 스택
### 📌 Backend
- Java 11, Spring Boot 3.2.4, MyBatis, Lombok

### 📌 Frontend
- HTML / CSS, JavaScript (ES6+), Bootstrap 5

### 📌 Database
- MySQL

---

## [구현 화면]
### [모든 책]
* 메인화면
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FrcRNz%2FbtsMpm3E8Jp%2F7awzZg64DR8iOiLOH8qvb0%2Fimg.png">

<br>
<br>

### [책등록/수정/조회/대여기록]
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbe5jZa%2FbtsMpJxvXB8%2FDPHFwT71lyDRuyA6iDrZU0%2Fimg.png">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbSLUX1%2FbtsMpqdReJJ%2F5AAwgUGtbJxUTCvJUBPvr0%2Fimg.png">
<Img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcrSatH%2FbtsMqIYXjDh%2FzlFekwey75iwR1iNWBiSx0%2Fimg.png">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbSm89n%2FbtsMoNAPmLD%2Fy3mixZrSCEC9wVCybKgAok%2Fimg.png">

<br>
<br>

### [나의책]
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FvHrKA%2FbtsMppF1oJy%2Fmf5j67OyFVHke17hLFHk7K%2Fimg.png">


<br>
<br>

### [빌린책]
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fo7TRt%2FbtsMo3KamGH%2F7lrcXUFpz75khKO2NlNRD0%2Fimg.png">
<br>

----

## [개발일지]
### Ver 1.0
- #001 [제작계기 & 사용기술스택 & 최소요구사항](https://ddururiiiiiii.tistory.com/431)
- #001 [데이터베이스를 생성하고 스프링부트에 Mybatis 설정하기 (회원목록 만들기)](https://ddururiiiiiii.tistory.com/432)
- #002 [회원 가입 / 회원조회(단건) / 회원정보수정 구현](https://ddururiiiiiii.tistory.com/440)
- #003 [책 등록 / 책 정보수정 / 책 목록조회 구현](https://ddururiiiiiii.tistory.com/441)
- #004 [타임리프 레이아웃 적용 및 네비게이션 바 생성/디자인 및 구현](https://ddururiiiiiii.tistory.com/442)
- #005 [데이터베이스 수정작업 그리고 그에 따른 XML 수정](https://ddururiiiiiii.tistory.com/443) 
- #006 [책 대여 / 반납 기능 구현](https://ddururiiiiiii.tistory.com/444)
- #007 [책 수정 / 삭제 기능 수정 및 구현](https://ddururiiiiiii.tistory.com/445)
- #008 [나의책 / 빌린책 기능 구현](https://ddururiiiiiii.tistory.com/446)
- #009 [로그인 / 로그아웃 / 회원가입 수정 및 구현](https://ddururiiiiiii.tistory.com/447)
- #010 [페이지네이션 적용하기 (회원목록 / 나의책 / 빌린책 / 모든책)](https://ddururiiiiiii.tistory.com/449)
- #011 [회원정보수정 수정하기](https://ddururiiiiiii.tistory.com/450)
- Ver 1.0 [[팀내도서대여시스템(OBRS) Ver.1)] 개발완료 보고 및 기능소개 / Ver2 개발예정인 기능](https://ddururiiiiiii.tistory.com/451)
- #012[AWS(아마존 웹 서비스) 회원가입 하기](https://ddururiiiiiii.tistory.com/462)
- #013[데이터베이스 컬럼추가 및 화면 수정](https://ddururiiiiiii.tistory.com/474)


----

### Ver 1.5
- #014 [도서 API 적용하기 (Feat. 카카오 도서 API)](https://ddururiiiiiii.tistory.com/580)
- #015 [대여내역 조회하기 (+ 썸네일 추가)](https://ddururiiiiiii.tistory.com/581)
- #016 [검색 기능 구현하기](https://ddururiiiiiii.tistory.com/582)
- #017 [로그인 인터셉터(Interceptor) 적용하기](https://ddururiiiiiii.tistory.com/583)

