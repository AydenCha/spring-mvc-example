# 실행 가이드 + JavaDoc 문서 확인 방법 (예제 프로젝트 README)

> 이 README는 **“이 코드를 어떻게 실행하고, 무엇을 확인해야 하는지”**에만 집중합니다.
> 코드 주석이 많아 읽기 어려우니, 상세 설명은 **JavaDoc HTML 문서**에서 확인하도록 구성합니다.

---

## 3) 예제 기능별 실행/확인 방법

> 아래는 "어떤 URL을 호출하면 어떤 화면/응답이 나오는지"만 정리합니다.
> 각 컨트롤러/DTO의 상세 계약(파라미터/응답/예외/주의점)은 **JavaDoc 문서에서 확인**합니다.

---

### 3-1. Coffee 이미지 업로드 (CoffeeController)

#### A. 업로드 폼 화면

* **GET** `/v1/coffees/upload`

브라우저에서 아래 URL 접속

* [http://localhost:8080/v1/coffees/upload](http://localhost:8080/v1/coffees/upload)

> 업로드 폼 템플릿: `templates/uploadForm.html`

#### B. 업로드 요청 (파일 저장)

* **POST** `/v1/coffees/upload`
* form-data 필드

    * `coffeeName` : 문자열
    * `image` : 파일

성공 시:

* 로컬 저장 경로: `./uploads/{originalFileName}`
* 결과 화면: `templates/uploadResult.html`

---

### 3-2. Member 등록/조회 (MemberController)

#### A. JSON 요청 → View 렌더링

* **POST** `/v1/members`
* Body: JSON

예시(curl)

```bash
curl -X POST http://localhost:8080/v1/members \
  -H "Content-Type: application/json" \
  -d '{"email":"test@test.com","name":"홍길동","phone":"010-1234-5678"}'
```

결과:

* View: `templates/memberResult.html`

#### B. JSON 요청 → JSON 응답

* **POST** `/v1/members/json`
* Body: JSON
* 응답: `200 OK` + JSON

예시(curl)

```bash
curl -X POST http://localhost:8080/v1/members/json \
  -H "Content-Type: application/json" \
  -d '{"email":"test@test.com","name":"홍길동","phone":"010-1234-5678"}'
```

#### C. 회원 등록 폼 페이지

* **GET** `/v1/members/form`

브라우저 접속

* [http://localhost:8080/v1/members/form](http://localhost:8080/v1/members/form)

> 템플릿: `templates/memberForm.html`

#### D. 회원 단건 페이지(PathVariable)

* **GET** `/v1/members/{member-id}`

예)

* [http://localhost:8080/v1/members/1](http://localhost:8080/v1/members/1)

> 템플릿: `templates/memberDetail.html`

#### E. 회원 목록 페이지

* **GET** `/v1/members`

> 템플릿: `templates/memberList.html`

#### F. Header 확인 페이지

* **GET** `/v1/members/header`
* Header 필요:

    * `User-Agent`
    * `x-custom-message`

예시(curl)

```bash
curl -X GET http://localhost:8080/v1/members/header \
  -H "x-custom-message: hello" \
  -H "User-Agent: test-agent"
```

> 템플릿: `templates/memberHeader.html`

#### G. Cookie 설정

* **GET** `/v1/members/set-cookie`

브라우저 접속 후 개발자 도구 → Application/Storage → Cookies에서 확인

> 템플릿: `templates/cookieSet.html`

---

### 3-3. Order 생성/조회 (OrderController)

#### A. 주문 생성

* **POST** `/v1/orders`
* 파라미터:

    * `memberId`
    * `coffeeId`

예시(curl)

```bash
curl -X POST "http://localhost:8080/v1/orders?memberId=1&coffeeId=10"
```

> 템플릿: `templates/orderResult.html`

#### B. 주문 단건 조회

* **GET** `/v1/orders/{order-id}`

예)

* [http://localhost:8080/v1/orders/100](http://localhost:8080/v1/orders/100)

> 템플릿: `templates/orderDetail.html`

#### C. 주문 목록

* **GET** `/v1/orders`

> 템플릿: `templates/orderList.html`

---

## 4) JavaDoc 문서로 “주석 대신” 확인하는 방법

> 코드에 주석이 많으니, 앞으로는 **JavaDoc HTML**을 열어서 확인하는 방식으로 봅니다.

### 4-1. IntelliJ에서 JavaDoc 생성

1. 상단 메뉴 `Tools` → `Generate JavaDoc...`
2. Output directory 지정 (추천)

    * `build/docs/javadoc` (Gradle 기본 출력과 맞추기 좋음)
3. Generate 실행
4. 생성된 파일 열기

    * `build/docs/javadoc/index.html`

---

### 4-2. Gradle로 JavaDoc 생성(추천)

```bash
./gradlew javadoc
```

생성 후 아래 파일을 브라우저로 열면 됩니다.

* `build/docs/javadoc/index.html`

---

### 4-3. 커맨드 라인(javadoc)로 직접 생성

```bash
javadoc -d docs/javadoc \
  -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 \
  -sourcepath src/main/java \
  -subpackages com.codeit.demo
```

생성 후:

* `docs/javadoc/index.html` 실행

---

## 5) JavaDoc에서 꼭 확인해야 하는 항목(실무 체크리스트)

JavaDoc을 열고 아래 정보를 확인하세요.

* **Controller 메서드**

    * 요청 URL / Method
    * 요청 파라미터(@RequestParam/@RequestBody/@PathVariable)
    * 반환 타입(View vs JSON)
    * 예외/주의사항

* **DTO**

    * 필드 의미(무엇을 담는 값인지)
    * null 가능 여부
    * 포맷/단위

* **Builder 예제(TestClass)**

    * builder 사용 시 설정하지 않은 필드의 기본값
    * 실무에서 builder를 쓰는 대표 사례

---

## 6) 예제 코드 설명 범위(이 프로젝트에서 다루는 것)

* Spring MVC: `@Controller` 기반의 View 렌더링 흐름
* JSON 요청/응답: `@RequestBody`, `@ResponseBody`, `ResponseEntity`
* 파라미터 바인딩: `@RequestParam`, `@PathVariable`, `@RequestHeader`
* 쿠키: `HttpServletResponse`로 Cookie 설정
* 파일 업로드: `MultipartFile` + `transferTo()`
* 문서화: JavaDoc으로 확인

---

## 7) 문제 발생 시 확인할 요소들

* 템플릿이 안 뜬다 → `resources/templates/*.html` 존재 여부 확인
* 406(Not Acceptable) → 요청 `Accept` 헤더와 반환 타입(View/JSON) 불일치 가능
* 파일 업로드 실패 → `./uploads` 생성 권한/경로 확인
* JSON 바인딩 실패 → DTO 기본 생성자(@NoArgsConstructor) 유무 확인

---