# "야모여" 프로젝트

## DB setup

```shell
$ cd docker/mysql
$ docker compose up -d
```

## API 테스트
### .http Client
`src/test/http` 하위 .http 파일로 현재 서버에 존재하는 API endpoint 를 테스트 할 수 있다.
### Swagger
프로젝트를 실행 후 브라우저에 http://localhost:8080/swagger.html 을 입력하면 swagger 페이지로 이동한다.

## 현재 기능
### 사용자
- 회원가입 / 로그인

### 소모임
- 소모임 생성 / 수정
  - 생성할땐 현재 로그인된 사용자가 모임장이 된다.
  - 소모임관련 수정은 모임장만 가능하다.
- 소모임 관심주제 수정
- 소모임 가입 신청
- 소모임 가입 승인 / 거절
  - 해당하는 소모임의 모임장만 승인 / 거절이 가능하다.

