# NEOS
- 대학생 스터디 모집 커뮤니티 플랫폼

# 프로젝트 목적 
대학생들을 위한 스터디 모집 및 정보 공유 서비스로써, 기본적인 커뮤니티 게시판과 프라이빗한 고민 상담 게시판 서비스도 운영하고 있으며, 원하는 자료를 사고파는 자료 상점을 통해 정보를 공유할 수 있고, 스터디 모집 기능으로 평소 알고 싶고 부족했던 부분을 같이 공유하며 역량을 향상 시킬 수 있는 웹서비스입니다.

# 구현 기술 스택 
- JDK 11.0.15
- Oracle (11g)
- Jpa
- SpringBoot 2.7.5
- Thymeleaf
- HTML, CSS, JS
- JQuery 3.6
- QueryDsl 5.0
- AJAX
- WebSocket
- Quartz
- OAuth 2.0 Naver/Kakao/Google
- KakaoMap api
- Summernote api 
- BootPay api
- Gmail api
 
# 테스트 환경
- Junit 5.0
- Postman

# Dependency
- Gradle


# 프로젝트에서 맡은 역할 
- OAuth2를 이용한 네이버/카카오/구글 로그인 회원가입
- 대학교 인증을 위한 gmail api를 사용해서 이메일 인증
- 스터디 모집 서비스 전체
- kakaomap api를 이용한 지도 검색 서비스(모집 장소 설정)
- interceptor에서 prehandle를 재정의하여 알림 파란점 표시
- aop를 사용한 알림 db 저장

# ERD
![ERD](./NEOS.drawio.png)

# 느낀 점
- 어려웠던 점과 해결방안 : 알림서비스를 구현하던 중 새로운 알림이 있을 경우 프로필에 파란점을 표시하는 기능이 있었는데, 이를 구현하기 위해 interceptor에서 prehandle을 재정의하여
컨트롤러로 이동하기 전 알림테이블에 값이 있는 지 여부를 검사하여 세션에 저장해두는 코드를 구현하였다. 처음에는 posthandle을 재정의했으나 이를 재정의할 시 response 객체가 이미 닫혀 다른 비동기 통신을 진행할 수 없었다. 따라서 posthandle을 재정의하여 response 객체가 닫히더라도 다시 비동기 통신을 할 수 있도록 하게 하였다.
- 느낀 점 : JSP와는 다르게 SpringBoot로 작업을 하니 모든 개발 부분에 있어서 확연한 속도 차이를 보였고, AOP와 interceptor 등 새로운 기술들을 배울 수 있어서 너무 흥미로웠다.
앞으로도 시시각각 발전되는 프로그래밍 기술에 뒤쳐지지 않게 여러분야를 공부하고 내 것으로 만들고 싶다는 생각을 하였다.


