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



