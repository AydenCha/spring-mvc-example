package com.codeit.demo.member.controller;

import com.codeit.demo.member.dto.MemberPostDto;
import com.codeit.demo.member.dto.MemberResponseDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 회원(Member) 관련 요청을 처리하는 Spring MVC 컨트롤러입니다.
 *
 * <p>
 * 이 컨트롤러는 다음과 같은 다양한 요청 처리 방식을 예제로 포함합니다.
 * </p>
 *
 * <ul>
 *   <li>@RequestBody 를 이용한 JSON 요청 처리</li>
 *   <li>View 반환(Thymeleaf 등 템플릿 렌더링)</li>
 *   <li>@ResponseBody + ResponseEntity 를 이용한 JSON 응답</li>
 *   <li>@PathVariable, @RequestHeader 사용</li>
 *   <li>Cookie 설정</li>
 * </ul>
 */
@Controller // 이 클래스가 Spring MVC 컨트롤러임을 선언
@RequestMapping("/v1/members")
// 이 컨트롤러의 모든 URL 앞에 "/v1/members"가 공통으로 붙음
public class MemberController {

    /*
     * ===============================
     * 1️⃣ 회원 등록 (JSON → View 반환)
     * ===============================
     */

    /**
     * 회원 정보를 JSON(RequestBody)으로 받아
     * View(HTML 템플릿)를 반환하는 회원 등록 처리 메서드입니다.
     *
     * <p><b>요청 정보</b></p>
     * <ul>
     *   <li>HTTP Method: POST</li>
     *   <li>URL: /v1/members</li>
     *   <li>Content-Type: application/json</li>
     * </ul>
     *
     * <p><b>요청 Body 예시</b></p>
     * <pre>
     * {
     *   "email": "test@test.com",
     *   "name": "홍길동",
     *   "phone": "010-1234-5678"
     * }
     * </pre>
     *
     * <p><b>처리 흐름</b></p>
     * <ol>
     *   <li>JSON 요청 바디를 MemberPostDto로 역직렬화</li>
     *   <li>Model에 값 저장</li>
     *   <li>memberResult.html 템플릿 렌더링</li>
     * </ol>
     *
     * @param dto 회원 등록 요청 데이터(JSON → DTO 바인딩)
     * @param model View로 전달할 데이터를 담는 객체
     * @return 회원 등록 결과 화면(View 이름)
     */
    @PostMapping
    // POST /v1/members 요청 처리
    public String postMember(@RequestBody MemberPostDto dto,
                             Model model) {

        model.addAttribute("email", dto.getEmail());
        // View에서 사용할 email 데이터 저장

        model.addAttribute("name", dto.getName());
        // View에서 사용할 name 데이터 저장

        model.addAttribute("phone", dto.getPhone());
        // View에서 사용할 phone 데이터 저장

        return "memberResult";
        // templates/memberResult.html 렌더링
    }

    /*
     * ===============================
     * 2️⃣ 회원 등록 (JSON → JSON 응답)
     * ===============================
     */

    /**
     * 회원 정보를 JSON으로 받아 JSON으로 응답하는 API 방식의 회원 등록 메서드입니다.
     *
     * <p>
     * View를 반환하지 않고,
     * HTTP Response Body에 JSON 데이터를 직접 내려줍니다.
     * </p>
     *
     * <p><b>요청 정보</b></p>
     * <ul>
     *   <li>HTTP Method: POST</li>
     *   <li>URL: /v1/members/json</li>
     *   <li>Content-Type: application/json</li>
     * </ul>
     *
     * @param dto 회원 등록 요청 데이터
     * @return HTTP 200 OK + MemberResponseDto(JSON)
     */
    @PostMapping(value = "/json")
    // POST /v1/members/json 요청 처리
    @ResponseBody
    // 반환값을 View가 아닌 HTTP Body(JSON)로 직접 응답
    public ResponseEntity postMemberJson(@RequestBody MemberPostDto dto) {

        MemberResponseDto result =
                new MemberResponseDto(dto.getName(), dto.getEmail(), dto.getPhone());
        // 응답 DTO 생성 (요청 데이터를 그대로 응답으로 사용)

        return ResponseEntity.ok(result);
        // HTTP 200 OK 상태 코드 + JSON 응답 반환
    }

    /*
     * ===============================
     * 3️⃣ 회원 등록 (Form 방식)
     * ===============================
     */

    /**
     * 회원 정보를 폼 데이터로 받아 View를 반환하는 메서드입니다.
     *
     * <p>
     * \@RequestBody 없이,
     * 요청 파라미터를 DTO로 자동 바인딩합니다.
     * </p>
     *
     * @param dto 폼 데이터가 바인딩된 MemberPostDto
     * @param model View로 전달할 데이터
     * @return 회원 등록 결과 화면
     */
    @PostMapping(value = "/form", consumes = "application/json")
    public String postMemberForm(MemberPostDto dto,
                                 Model model) {

        model.addAttribute("email", dto.getEmail());
        model.addAttribute("name", dto.getName());
        model.addAttribute("phone", dto.getPhone());

        return "memberResult";
    }

    /*
     * ===============================
     * 4️⃣ 회원 등록 폼 페이지 조회
     * ===============================
     */

    /**
     * 회원 등록 폼 화면을 반환합니다.
     *
     * @return 회원 등록 폼 View
     */
    @GetMapping("/form")
    public String getMemberFormPage() {
        return "memberForm";
        // templates/memberForm.html
    }

    /*
     * ===============================
     * 5️⃣ PathVariable 사용 예제
     * ===============================
     */

    /**
     * URL 경로 변수(PathVariable)를 이용해
     * 특정 회원 상세 페이지를 조회합니다.
     *
     * @param memberId URL 경로에서 추출한 회원 ID
     * @param model View로 전달할 데이터
     * @return 회원 상세 화면
     */
    @GetMapping("/{member-id}")
    public String getMember(@PathVariable("member-id") int memberId,
                            Model model) {

        model.addAttribute("memberId", memberId);
        // View에서 회원 ID 출력용

        return "memberDetail";
        // templates/memberDetail.html
    }

    /*
     * ===============================
     * 6️⃣ 회원 목록 페이지
     * ===============================
     */

    /**
     * 회원 목록 페이지를 반환합니다.
     *
     * @param model View로 전달할 데이터
     * @return 회원 목록 화면
     */
    @GetMapping
    public String getMembers(Model model) {
        return "memberList";
        // templates/memberList.html
    }

    /*
     * ===============================
     * 7️⃣ RequestHeader 사용 예제
     * ===============================
     */

    /**
     * HTTP Header 값을 읽어 View로 전달하는 예제 메서드입니다.
     *
     * @param userAgent User-Agent 헤더 값
     * @param customMessage 커스텀 헤더(x-custom-message)
     * @param model View로 전달할 데이터
     * @return 헤더 정보 출력 화면
     */
    @GetMapping("/header")
    public String getUserAgent(@RequestHeader("User-Agent") String userAgent,
                               @RequestHeader("x-custom-message") String customMessage,
                               Model model) {

        model.addAttribute("userAgent", userAgent);
        model.addAttribute("customMessage", customMessage);

        return "memberHeader";
    }

    /*
     * ===============================
     * 8️⃣ Cookie 설정 예제
     * ===============================
     */

    /**
     * HTTP 응답에 쿠키를 설정하는 예제 메서드입니다.
     *
     * @param response HTTP 응답 객체
     * @return 쿠키 설정 완료 화면
     */
    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("userToken", "catluckymeow");
        // 이름이 userToken인 쿠키 생성

        cookie.setPath("/");
        // 모든 경로에서 쿠키 사용 가능

        cookie.setHttpOnly(true);
        // JavaScript에서 접근 불가(XSS 방지)

        cookie.setMaxAge(3600);
        // 쿠키 유효 시간: 1시간(초 단위)

        response.addCookie(cookie);
        // HTTP 응답에 쿠키 추가

        return "cookieSet";
        // templates/cookieSet.html
    }
}
