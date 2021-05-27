## Spring Security 로그인/회원가입 My Page 구현


## 개요

 * 스프링 시큐리티를 활용한 로그인/회원가입/ My Page구현 연습
 * 개발 인원 : 1명
 * 개발 환경 : Spring Boot,Spring Security, Thymeleaf, Mybatis
 * 데이터베이스 : Oracle DataBase => MySql 로 변경 


## 목표

 1. Spring Security를 이용하여 로그인 및 회원가입 구현
 2. 회원가입 시 이메일 인증 적용
 3. 비밀번호 찾기, 회원 아이디 찾기 등 구현
 4. 게시판 프로젝트와 병합하여 사용 => MyPage로 변경

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

### 2021.05.15
 1. 로그인, 회원가입 및 권한 부여 구현
 2. 권한에 따른 페이지 조회 설정 완료


### 2021.05.16
 1. 데이터베이스 ORACLE 에서 MYSQL로 변경
    
> **변경 사유**
    
     a. 오라클 보다 훨씬 가벼운 운영체제
      <br/>
     b. 작성시 보다 짧은 짧아진 쿼리문
        - paging 쿼리문 작성 중 limit 함수를 사용하여 페이지에 보여줄 게시글 수를 조절 가능
     c. 현업에서 더 많이 사용되는 MySQL 및 MariaDB 숙달을 위함


<br/>

> **oracle 쿼리문 : paging**


 ```java
      <![CDATA[ 
      select * from (
          select /* index_asr(file_board pk_board)   */ rownum rn, b_no, title, WRITER, reg_date
          from FILE_BOARD
          where  B_NO > 0 and ROWNUM <= (#{pageNum} * #{amount})
      )
      where rn > (#{pageNum}-1) * #{amount}
      ]]>     
```

 <br/>
  
> **MySQL 쿼리문 : paging**

     
```jave         
        select * from memberList
        order by no desc
        limit #{pageStart},#{amount};
```

 <br/>
     
     

 
### 2021.05.27
 1. 가입 시 등록한 email를 통해 비밀번호 찾기 기능 구현
    * 난수를 이용한 비밀번호 암호화
    * 임시 비밀번호 생성과 동시에 db 저장 및 메일 발송  
 2. 권한에 따른 보여지는 페이지 구분
    * 회원일 경우 자신의 정보만 접근 가능
    * 관리자의 경우 모든 회원리스트를 조회 및 삭제 가능

<br/>
  
# 프로젝트 중 직면한 문제
  
  
 **첫번 째 문제 **
 
 
 <br/>
 
   - Spring Security 연동 시 CSS/JS 까지 연동이 안되는 문제 발생
   - ignore 까지 했지만 안되는 현상이 발생하여 문제 추적 중
   - 하지만 회원 로그인을 할 경우 제대로 CSS/JS가 적용 되었다.


<br/>

 **2.1 원인 및 해결**
 
   - Spring Security 특징 인 인증/인가에 따라 권한에 따라 URL의 접근 여부를 결정할 수 있다. 
   - 기존의 설정에는 'ROLE_USER' 이상의 권한을 가진 자만 CSS/JS를 접근할 수 있었기에 비회원은 CSS/JS가 미적용된 페이지를 보게되었다.
   - 그리하여 CSS/JS는 모든 클라이언트가 접근할 수 있도록 설정을 변경해줌으로 써 문제를 해결할 수 있었다.
   
   ```java
           http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/mypage/**").access("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")

                //누구나 접속할 수 있는 페이지이기 때문에 누구나 접근이 가능하다 (.permitAll())
                .antMatchers("/main",
                        "/login",
                        "/sign-Up",
                        "/pwFind",
                        "/login/**",
                        "/community/**",
                        "/main",
                        "/pwFind",
                        "/css/**","/js/**","/img/**","/plugin/**","/vendor/**",** // CSS/JS 같은 정적인 데이터는 누구나 접근할 수 있도록 설정 변경
                        "/logout"
                ).permitAll()

                //기타 /** 의 경로는 인증을 필요로 한다
                .antMatchers("/**").authenticated();
   
   ```


 **두번 째 문제**
 
 
 <br/>
   - 로그인 시 페이지가 제대로 넘어가질 않는 문제 발생

 **2.1 원인 및 추론**
 
 <br/>
 
   - 로그인 이후 부가작업을 진행하기 위해 **SimpleUrlAuthenticationSuccessHandler.class** 중 **onAuthenticationSuccess** 메서드를 상속받을 때 **FilterChain** 이 포함된 메서드를 상속받아 로그인 이후 페이지로 넘어가질 못하였다. 
   
  ```java      @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        **FilterChain chain,**
                                        Authentication authentication) throws IOException, ServletException { 
  ```
  
  
   - 하지만 확장 및 설정을 고려하지 않은 상황에서 메서드에 포함이 되어 있어 404 error가 발생하였다. 심지어 url조차 전달이 되지 않았다.
   ![image](https://user-images.githubusercontent.com/63430211/118206624-da48c780-b49d-11eb-9757-363af28b323c.png)
  
  <br/>
  
  
  **2.2 해결 및 추론**  
  
  
  <br/>
  
  
   - FilterChain이 포함되어있지않는 메서드를 다시 오버로딩하여 사용함으로써 로그인 이후 페이지로 넘어갔다
   - 첫 서버 running 시 진행한 Filter이 외 Filter에 대한 접근이 없기에 정상적으로 진행된 것으로 생각이 든다. 
   
   ```java   @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException { 
  ```
 <br/>
 
 
 **2.3 공부한 내용**  
  
 <br/>
 
 
  - FilterChain은 Chain 구조로 이루어져 있는 스프링 시큐리티 내부의 필터들이며 요청을 받아 처리를 한다. 
  - 개발 시 해당필터를 확장하고 설정하면 스프링 시큐리티를 이용해 다양한 형태로 로그인 처리가 가능하다. 
  - 개발 시 IDE를 신뢰하고 인자 값들을 제대로 확인하지 못한 점에 대해서 크게 반성을 한다. 
  - 특히나 보안을 작업할 땐 꼼꼼하게 확인해야 한다는 것과 작은 실수하나로 사이트 전체를 멈춰버릴 수 있다는 것을 배울 수 있었다. 

**2.4 더 이해가 필요한 부분**
  - Filter에 대한 개념과 진행방법에 대해 더 깊은 이해가 필요할 것으로 판단 됨
  
