package com.codeit.demo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원(Member) 정보에 대한 응답(Response) 데이터를 전달하기 위한 DTO(Data Transfer Object) 클래스입니다.
 *
 * <p>
 * 이 DTO는 컨트롤러에서 클라이언트로
 * JSON 형태의 응답 데이터를 내려줄 때 사용됩니다.
 * </p>
 *
 * <p><b>이 DTO의 설계 목적</b></p>
 * <ul>
 *   <li>회원 조회 / 회원 등록 결과 응답용 데이터 표현</li>
 *   <li>View 또는 API 응답(JSON) 전용 객체</li>
 *   <li>요청(Request) DTO와 역할 분리</li>
 * </ul>
 *
 * <p><b>주요 사용 위치</b></p>
 * <ul>
 *   <li>@RestController 또는 @ResponseBody 기반 API 응답</li>
 *   <li>ResponseEntity의 Body로 사용</li>
 * </ul>
 *
 * <p><b>Lombok 애너테이션 설명</b></p>
 * <ul>
 *   <li>{@code @AllArgsConstructor} :
 *       모든 필드를 파라미터로 받는 생성자를 자동으로 생성합니다.</li>
 *   <li>{@code @Getter} :
 *       모든 필드에 대한 getter 메서드를 자동으로 생성합니다.</li>
 *   <li>{@code @Builder} :
 *       빌더 패턴을 통해 객체를 생성할 수 있습니다.
 *       (현재는 주석 처리되어 있음)</li>
 * </ul>
 */
@AllArgsConstructor // name, email, phone을 파라미터로 받는 전체 생성자 자동 생성
@Getter              // 모든 필드에 대한 getter 메서드 자동 생성
//@Builder           // 빌더 패턴 사용 시 활성화 가능 (불변 객체 설계에 유리)
public class MemberResponseDto { // 회원 응답 데이터를 담는 DTO 클래스 선언

    /**
     * 회원의 이름을 나타내는 필드입니다.
     *
     * <p>
     * 클라이언트 응답(JSON)에서 "name" 필드로 전달됩니다.
     * </p>
     */
    private String name; // 회원 이름

    /**
     * 회원의 이메일 주소를 나타내는 필드입니다.
     *
     * <p>
     * 클라이언트 응답(JSON)에서 "email" 필드로 전달됩니다.
     * </p>
     */
    private String email; // 회원 이메일

    /**
     * 회원의 전화번호를 나타내는 필드입니다.
     *
     * <p>
     * 클라이언트 응답(JSON)에서 "phone" 필드로 전달됩니다.
     * </p>
     */
    private String phone; // 회원 전화번호
}