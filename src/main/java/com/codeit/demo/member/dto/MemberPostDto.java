package com.codeit.demo.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원(Member) 등록 요청 시 사용되는 DTO(Data Transfer Object) 클래스입니다.
 *
 * <p>
 * 클라이언트가 전송한 회원 정보(JSON 또는 Form 데이터)를
 * 컨트롤러 계층에서 안전하게 전달받기 위한 목적의 객체입니다.
 * </p>
 *
 * <p><b>이 DTO의 역할</b></p>
 * <ul>
 *   <li>회원 등록 요청 데이터를 하나의 객체로 묶음</li>
 *   <li>Controller ↔ View / Controller ↔ Service 간 데이터 전달</li>
 *   <li>엔티티(Entity)와 분리된 순수 데이터 전달 객체</li>
 * </ul>
 *
 * <p><b>사용 시나리오</b></p>
 * <ul>
 *   <li>@RequestBody 를 통한 JSON 요청 바인딩</li>
 *   <li>Form 데이터 자동 바인딩</li>
 * </ul>
 *
 * <p><b>Lombok 애너테이션 설명</b></p>
 * <ul>
 *   <li>{@code @Getter} :
 *       모든 필드에 대해 getter 메서드를 자동 생성합니다.</li>
 *   <li>{@code @Setter} :
 *       모든 필드에 대해 setter 메서드를 자동 생성합니다.</li>
 *   <li>{@code @NoArgsConstructor} :
 *       파라미터가 없는 기본 생성자를 자동 생성합니다.</li>
 *   <li>{@code @Builder} :
 *       빌더 패턴을 통해 객체를 생성할 수 있도록 합니다.
 *       (현재는 주석 처리되어 있으며, 활성화 시 생성자 제약에 주의해야 합니다)</li>
 * </ul>
 */
@Getter           // email, name, phone 필드에 대한 getter 메서드 자동 생성
@NoArgsConstructor // 기본 생성자(MemberPostDto()) 자동 생성
@Setter           // email, name, phone 필드에 대한 setter 메서드 자동 생성
//@Builder        // 빌더 패턴 사용 시 활성화 가능 (불변 객체 설계 시 주로 사용)
public class MemberPostDto { // 회원 등록 요청 데이터를 담는 DTO 클래스 선언

    /**
     * 회원의 이메일 주소를 저장하는 필드입니다.
     *
     * <p>
     * 클라이언트 요청(JSON 또는 Form 데이터)의
     * "email" 값이 이 필드에 바인딩됩니다.
     * </p>
     *
     * <p>
     * 예:
     * <pre>
     * {
     *   "email": "test@test.com"
     * }
     * </pre>
     * </p>
     */
    private String email; // 회원 이메일

    /**
     * 회원의 이름을 저장하는 필드입니다.
     *
     * <p>
     * 요청 데이터의 "name" 필드와 매핑되며,
     * 사용자 식별용 정보로 사용됩니다.
     * </p>
     */
    private String name; // 회원 이름

    /**
     * 회원의 전화번호를 저장하는 필드입니다.
     *
     * <p>
     * 요청 데이터의 "phone" 필드와 매핑됩니다.
     * </p>
     *
     * <p>
     * 예:
     * <pre>
     * {
     *   "phone": "010-1234-5678"
     * }
     * </pre>
     * </p>
     */
    private String phone; // 회원 전화번호
}