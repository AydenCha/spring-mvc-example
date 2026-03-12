package com.codeit.demo.coffee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 커피 이미지 업로드와 관련된 데이터를 전달하기 위한 DTO(Data Transfer Object) 클래스입니다.
 *
 * <p>
 * 이 클래스는 컨트롤러, 서비스, 뷰 계층 사이에서
 * "커피 이름"과 "업로드된 이미지 파일명" 정보를 묶어서 전달하는 용도로 사용됩니다.
 * </p>
 *
 * <p><b>DTO 설계 목적</b></p>
 * <ul>
 *   <li>엔티티(Entity)와 분리된 단순 데이터 전달용 객체</li>
 *   <li>요청/응답 또는 계층 간 데이터 이동 시 사용</li>
 *   <li>비즈니스 로직을 포함하지 않음</li>
 * </ul>
 *
 * <p><b>Lombok 애너테이션 설명</b></p>
 * <ul>
 *   <li>{@code @NoArgsConstructor} :
 *       파라미터가 없는 기본 생성자를 자동으로 생성합니다.</li>
 *   <li>{@code @Getter} :
 *       모든 필드에 대해 getter 메서드를 자동으로 생성합니다.</li>
 *   <li>{@code @Setter} :
 *       모든 필드에 대해 setter 메서드를 자동으로 생성합니다.</li>
 * </ul>
 */
@NoArgsConstructor // 기본 생성자(CoffeeImageDto())를 자동 생성
@Getter             // coffeeName, fileName 필드에 대한 getter 메서드 자동 생성
@Setter             // coffeeName, fileName 필드에 대한 setter 메서드 자동 생성
public class CoffeeImageDto { // 커피 이미지 정보를 담는 DTO 클래스 선언

    /**
     * 업로드된 커피의 이름을 저장하는 필드입니다.
     *
     * <p>
     * 사용 예:
     * <pre>
     * dto.setCoffeeName("아메리카노");
     * </pre>
     * </p>
     */
    private String coffeeName; // 커피 이름(텍스트 정보)

    /**
     * 업로드된 이미지 파일의 파일명을 저장하는 필드입니다.
     *
     * <p>
     * 서버에 저장된 실제 파일명이며,
     * 클라이언트가 업로드한 원본 파일명과 동일할 수 있습니다.
     * </p>
     *
     * <p>
     * 사용 예:
     * <pre>
     * dto.setFileName("americano.png");
     * </pre>
     * </p>
     */
    private String fileName; // 이미지 파일명
}