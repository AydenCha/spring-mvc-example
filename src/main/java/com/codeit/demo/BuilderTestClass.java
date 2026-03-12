package com.codeit.demo;

import lombok.*;

/**
 * Lombok의 {@code @Builder} 애너테이션 동작을 확인하기 위한 테스트 클래스입니다.
 *
 * <p>
 * 이 클래스는 main 메서드를 통해
 * 빌더 패턴으로 객체를 생성하는 예제를 실행합니다.
 * </p>
 */
public class BuilderTestClass {

    /**
     * 프로그램의 시작점(main 메서드)입니다.
     *
     * <p>
     * Lombok {@code @Builder}를 사용해
     * {@link TestClass} 객체를 생성하는 예제를 보여줍니다.
     * </p>
     *
     * @param args JVM 실행 시 전달되는 커맨드라인 인자
     */
    public static void main(String[] args) {

        TestClass testClass = TestClass.builder()
                // @Builder에 의해 자동 생성된 builder() 메서드 호출

                .id(1)
                // TestClass의 id 필드 값 설정

                .name("김러키")
                // TestClass의 name 필드 값 설정

                .build();
        // build() 호출 시 TestClass 객체 생성

        // ⚠️ 주의:
        // age, address는 builder에서 설정하지 않았으므로
        // int 타입은 기본값 0, String 타입은 null로 초기화됨
    }
}


/*
 * ===============================
 * Lombok @Builder 대상 클래스
 * ===============================
 */

//@AllArgsConstructor
// → 모든 필드를 파라미터로 받는 생성자 자동 생성
// → @Builder와 함께 사용하면 생성자 기반 빌더가 생성됨

//@NoArgsConstructor
// → 파라미터 없는 기본 생성자 자동 생성
// → @Builder와 함께 사용 시 의도하지 않은 객체 생성이 가능해질 수 있음

//@Setter
// → 모든 필드에 대한 setter 자동 생성
// → 불변 객체 설계에는 부적합하므로 보통 Builder와 함께 사용하지 않음

/**
 * {@code @Builder} 패턴을 적용한 테스트용 클래스입니다.
 *
 * <p>
 * 이 클래스는 여러 필드를 가진 객체를
 * 안전하고 가독성 좋게 생성하기 위해
 * 빌더 패턴을 사용하는 예제를 보여줍니다.
 * </p>
 *
 * <p><b>설계 의도</b></p>
 * <ul>
 *   <li>생성자 파라미터 순서 실수 방지</li>
 *   <li>필요한 필드만 선택적으로 설정</li>
 *   <li>객체 생성 코드의 가독성 향상</li>
 * </ul>
 */
@Builder
// Lombok이 내부적으로 TestClassBuilder 클래스를 생성하고
// builder(), build() 메서드를 자동 생성
@Getter
// 모든 필드에 대한 getter 메서드 자동 생성
class TestClass {

    /**
     * 객체의 고유 식별자(ID)입니다.
     */
    private int id;
    // primitive 타입이므로 기본값은 0

    /**
     * 객체의 이름을 나타내는 필드입니다.
     */
    private String name;
    // 참조 타입이므로 기본값은 null

    /**
     * 객체의 나이를 나타내는 필드입니다.
     */
    private int age;
    // builder에서 설정하지 않으면 기본값 0

    /**
     * 객체의 주소 정보를 나타내는 필드입니다.
     */
    private String address;
    // builder에서 설정하지 않으면 null
}
