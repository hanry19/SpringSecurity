## Spring Security 로그인/회원가입 구현 + 게시판 병합


## 개요

 * 스프링 시큐리티를 활용한 로그인/회원가입 구현 연습
 * 개발 인원 : 1명
 * 개발 환경 : Spring Boot,Spring Security, Thymeleaf, Mybatis
 * 데이터베이스 : Oracle DataBase


## 목표

 1. Spring Security를 이용하여 로그인 및 회원가입 구현
 2. 회원가입 시 이메일 인증 적용
 3. 비밀번호 찾기, 회원 아이디 찾기 등 구현
 4. 게시판 프로젝트와 병합하여 사용

## API 설계
![image](https://user-images.githubusercontent.com/63430211/118133138-6e357780-b43b-11eb-824d-c6fbae9b2f7a.png)
 
![image](https://user-images.githubusercontent.com/63430211/118133172-77bedf80-b43b-11eb-8148-942e9af170cb.png)

## 개발일지


### 2021.05.05
 1. 로그인 및 회원가입 구현 클론 진행(jsp)

### 2021.05.09
 1. View단 JSP에서 thymeleaf 로 변경

### 2021.05.12
 1. Spring Security + 게시판 병합
 2. Spring Security 로그인 시 성공/실패 로직 구현 (Provider 관련)

### 2021.05.13
 **1. 문제 1**
   - Spring Security 연동 시 JS, CSS 까지 연동이 안되는 문제 발생
   - ignore 까지 했지만 안되는 현상이 발생하여 문제 추적 중

 **2. 문제 2**
   - 로그인 시 페이지가 제대로 넘어가질 않는 문제 발생
