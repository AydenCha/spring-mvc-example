package com.codeit.demo.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 주문(Order) 관련 요청을 처리하는 Spring MVC 컨트롤러입니다.
 *
 * <p>
 * 이 컨트롤러는 주문 도메인을 대상으로 다음 기능을 제공합니다.
 * </p>
 *
 * <ul>
 *   <li>주문 생성</li>
 *   <li>주문 단건 조회</li>
 *   <li>주문 목록 조회</li>
 * </ul>
 *
 * <p>
 * 모든 요청은 화면(View)을 반환하는 방식으로 처리되며,
 * Thymeleaf 같은 템플릿 엔진과 함께 사용되는 것을 전제로 합니다.
 * </p>
 */
@Controller
// 이 클래스가 Spring MVC Controller임을 선언
@RequestMapping("/v1/orders")
// 이 컨트롤러에 매핑되는 모든 요청 URL의 공통 prefix
public class OrderController {

    /*
     * ===============================
     * 1️⃣ 주문 생성
     * ===============================
     */

    /**
     * 주문 생성 요청을 처리하는 메서드입니다.
     *
     * <p><b>요청 정보</b></p>
     * <ul>
     *   <li>HTTP Method: POST</li>
     *   <li>URL: /v1/orders</li>
     * </ul>
     *
     * <p><b>요청 파라미터</b></p>
     * <ul>
     *   <li>memberId : 주문을 생성한 회원의 ID</li>
     *   <li>coffeeId : 주문한 커피의 ID</li>
     * </ul>
     *
     * <p><b>요청 예시 (Form / Query Parameter)</b></p>
     * <pre>
     * memberId=1&coffeeId=10
     * </pre>
     *
     * <p><b>처리 흐름</b></p>
     * <ol>
     *   <li>요청 파라미터(memberId, coffeeId)를 @RequestParam으로 바인딩</li>
     *   <li>Model에 주문 관련 데이터를 저장</li>
     *   <li>주문 결과 화면(orderResult.html) 반환</li>
     * </ol>
     *
     * @param memberId 주문을 요청한 회원의 ID
     * @param coffeeId 주문한 커피의 ID
     * @param model View로 전달할 데이터를 담는 객체
     * @return 주문 결과 화면(View 이름)
     */
    @PostMapping
    // POST /v1/orders 요청을 처리
    public String postOrder(@RequestParam("memberId") long memberId,
                            // 요청 파라미터 "memberId"를 long 타입으로 바인딩

                            @RequestParam("coffeeId") long coffeeId,
                            // 요청 파라미터 "coffeeId"를 long 타입으로 바인딩

                            Model model) {
        // View로 데이터를 전달하기 위한 Model 객체

        model.addAttribute("memberId", memberId);
        // 주문 결과 화면에서 회원 ID를 출력하기 위해 Model에 저장

        model.addAttribute("coffeeId", coffeeId);
        // 주문 결과 화면에서 커피 ID를 출력하기 위해 Model에 저장

        return "orderResult";
        // templates/orderResult.html 렌더링
    }

    /*
     * ===============================
     * 2️⃣ 주문 단건 조회
     * ===============================
     */

    /**
     * 특정 주문 ID에 해당하는 주문 상세 정보를 조회하는 메서드입니다.
     *
     * <p><b>요청 정보</b></p>
     * <ul>
     *   <li>HTTP Method: GET</li>
     *   <li>URL: /v1/orders/{order-id}</li>
     * </ul>
     *
     * <p><b>요청 예시</b></p>
     * <pre>
     * GET /v1/orders/100
     * </pre>
     *
     * <p><b>처리 흐름</b></p>
     * <ol>
     *   <li>URL 경로에 포함된 주문 ID를 @PathVariable로 추출</li>
     *   <li>Model에 주문 ID 저장</li>
     *   <li>주문 상세 화면(orderDetail.html) 반환</li>
     * </ol>
     *
     * @param orderId URL 경로에서 추출한 주문 ID
     * @param model View로 전달할 데이터를 담는 객체
     * @return 주문 상세 화면(View 이름)
     */
    @GetMapping("{order-id}")
    // GET /v1/orders/{order-id} 요청 처리
    public String getOrder(@PathVariable("order-id") long orderId,
                           // URL 경로 변수 "order-id"를 long 타입으로 바인딩

                           Model model) {
        // View로 전달할 데이터를 담는 Model

        model.addAttribute("orderId", orderId);
        // 주문 상세 화면에서 주문 ID를 출력하기 위해 Model에 저장

        return "orderDetail";
        // templates/orderDetail.html 렌더링
    }

    /*
     * ===============================
     * 3️⃣ 주문 목록 조회
     * ===============================
     */

    /**
     * 주문 목록 화면을 조회하는 메서드입니다.
     *
     * <p><b>요청 정보</b></p>
     * <ul>
     *   <li>HTTP Method: GET</li>
     *   <li>URL: /v1/orders</li>
     * </ul>
     *
     * <p>
     * 현재 예제 코드에서는 주문 목록 데이터를 실제로 조회하지 않고,
     * 단순히 화면(View)만 반환합니다.
     * </p>
     *
     * @param model View로 전달할 데이터를 담는 객체
     * @return 주문 목록 화면(View 이름)
     */
    @GetMapping
    // GET /v1/orders 요청 처리
    public String getOrders(Model model) {

        return "orderList";
        // templates/orderList.html 렌더링
    }
}