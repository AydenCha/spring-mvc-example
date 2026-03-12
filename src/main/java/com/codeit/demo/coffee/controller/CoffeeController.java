package com.codeit.demo.coffee.controller;

import com.codeit.demo.coffee.dto.CoffeeImageDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 커피 이미지 업로드를 처리하는 Spring MVC 컨트롤러입니다.
 *
 * <p>주요 기능</p>
 * <ul>
 *   <li>업로드 폼 페이지 반환(GET)</li>
 *   <li>업로드된 파일을 서버 로컬 디렉터리에 저장(POST)</li>
 *   <li>업로드 결과(커피 이름, 파일명)를 View로 전달</li>
 * </ul>
 */
@Controller // 이 클래스가 Spring MVC 컨트롤러임을 선언(요청을 받아서 View 이름을 반환하는 역할)
@RequestMapping("/v1/coffees") // 이 컨트롤러의 모든 매핑 URL 앞에 "/v1/coffees"가 공통으로 붙음
public class CoffeeController { // 커피 관련 요청을 처리하는 컨트롤러 클래스 시작

    /**
     * 이미지 업로드 폼 화면을 반환합니다.
     *
     * <p><b>요청</b></p>
     * <ul>
     *   <li>HTTP Method: GET</li>
     *   <li>URL: /v1/coffees/upload</li>
     * </ul>
     *
     * <p><b>동작</b></p>
     * <ul>
     *   <li>사용자에게 파일 업로드 폼(예: uploadForm.html)을 렌더링하도록 View 이름을 반환합니다.</li>
     * </ul>
     *
     * <p><b>반환값</b></p>
     * @return 업로드 폼 템플릿의 View 이름(예: "uploadForm")
     */
    @GetMapping("/upload") // "/v1/coffees/upload" 경로로 들어오는 GET 요청을 이 메서드가 처리
    public String getUpload() { // 업로드 페이지를 반환하는 컨트롤러 메서드
        return "uploadForm"; // View Resolver가 "uploadForm" 템플릿(예: uploadForm.html)을 찾아 렌더링
    }

    /**
     * 커피 이름과 이미지 파일을 업로드받아 서버 로컬 디렉터리(./uploads)에 저장하고,
     * 업로드 결과 화면으로 전달할 데이터를 Model에 담아 반환합니다.
     *
     * <p><b>요청</b></p>
     * <ul>
     *   <li>HTTP Method: POST</li>
     *   <li>URL: /v1/coffees/upload</li>
     *   <li>Content-Type: multipart/form-data</li>
     * </ul>
     *
     * <p><b>요청 파라미터</b></p>
     * <ul>
     *   <li>coffeeName: 커피 이름(텍스트)</li>
     *   <li>image: 업로드할 이미지 파일(MultipartFile)</li>
     * </ul>
     *
     * <p><b>처리 흐름</b></p>
     * <ol>
     *   <li>업로드 파일의 원본 파일명을 가져옵니다.</li>
     *   <li>./uploads/ 경로 아래에 파일이 저장될 경로(Path)를 생성합니다.</li>
     *   <li>uploads 디렉터리가 없으면 생성합니다.</li>
     *   <li>MultipartFile#transferTo()로 실제 파일을 로컬 디렉터리에 저장합니다.</li>
     *   <li>DTO를 생성하여 커피명/파일명 정보를 담습니다(현재 코드는 DTO 생성까지만 하고 사용은 안 함).</li>
     *   <li>View에서 출력할 값을 Model에 담고, 결과 페이지(View)를 반환합니다.</li>
     * </ol>
     *
     * <p><b>예외</b></p>
     * <ul>
     *   <li>IOException: 디렉터리 생성 또는 파일 저장 과정에서 I/O 오류가 발생할 경우</li>
     * </ul>
     *
     * @param coffeeName 사용자가 입력한 커피 이름(폼 필드 "coffeeName" 값이 바인딩됨)
     * @param image 업로드된 이미지 파일(폼 필드 "image"가 MultipartFile로 바인딩됨)
     * @param model View(템플릿)에 전달할 데이터를 담는 객체
     * @return 업로드 결과 템플릿의 View 이름(예: "uploadResult")
     * @throws IOException 파일 저장 관련 I/O 오류 발생 시 던져짐
     */
    @PostMapping("/upload") // "/v1/coffees/upload" 경로로 들어오는 POST 요청(파일 업로드)을 이 메서드가 처리
    public String uploadCoffeeImage(@RequestParam("coffeeName") String coffeeName, // 폼/쿼리 파라미터 "coffeeName" 값을 String으로 바인딩
                                    @RequestParam("image") MultipartFile image, // 멀티파트 폼 필드 "image"를 MultipartFile로 바인딩
                                    Model model) throws IOException { // View로 넘길 값을 담을 Model + 파일 처리 중 IOException 발생 가능

        String fileName = image.getOriginalFilename(); // 업로드된 파일의 원본 파일명(클라이언트가 보낸 이름)을 가져옴

        Path savePath = Paths.get("./uploads/" + fileName); // 현재 프로젝트 실행 디렉터리 기준 "./uploads/파일명" 저장 경로 생성

        Files.createDirectories(savePath.getParent()); // savePath의 부모 디렉터리("./uploads")가 없으면 생성(이미 있으면 그대로 통과)

        image.transferTo(savePath); // 실제 파일 데이터를 savePath 위치에 저장(임시 업로드 파일 -> 지정 경로로 이동/복사)

        CoffeeImageDto dto = new CoffeeImageDto(); // 커피 이미지 정보를 담기 위한 DTO 객체 생성
        dto.setCoffeeName(coffeeName); // DTO에 커피 이름 저장
        dto.setFileName(fileName); // DTO에 파일명 저장
        // ※ 현재 코드는 dto를 생성해 값만 세팅하고, 실제로 model에 넣거나 저장소에 저장하는 로직은 없음

        model.addAttribute("fileName", fileName); // View에서 사용할 "fileName" 속성을 Model에 추가
        model.addAttribute("coffeeName", coffeeName); // View에서 사용할 "coffeeName" 속성을 Model에 추가

        return "uploadResult"; // 업로드 결과를 보여줄 템플릿(View) 이름 반환
    }

    // ===========================
    // 아래는 "다중 파일 업로드" 예시 코드(주석 처리된 상태)
    // ===========================

    //@PostMapping("/upload") // (예시) 같은 URL에 대한 POST 매핑 - 실제 사용 시 위 메서드와 충돌하므로 둘 중 하나만 살아있어야 함
    //public String uploadCoffeeImage(@RequestParam("coffeeName") String coffeeName, // 커피 이름 파라미터 바인딩
    //                                @RequestParam("image") List<MultipartFile> images, // (예시) 여러 파일을 List로 바인딩
    //                                Model model) throws IOException { // Model + IOException 가능
    //        List<String> fileNames = new ArrayList<>(); // 업로드된 파일명들을 담을 리스트 생성
    //        for(MultipartFile file : images) { // 업로드된 파일들을 하나씩 순회
    //            String fileName = file.getOriginalFilename(); // 각 파일의 원본 파일명 추출
    //            Path savePath = Paths.get("./uploads/" + fileName); // 저장할 경로 생성
    //            Files.createDirectories(savePath.getParent()); // 업로드 폴더가 없으면 생성
    //            file.transferTo(savePath); // 파일을 로컬에 저장
    //            fileNames.add(fileName); // 저장된 파일명을 리스트에 추가
    //        }
    //
    ////    CoffeeImageDto dto = new CoffeeImageDto(); // (예시) DTO 생성
    ////    dto.setCoffeeName(coffeeName); // (예시) DTO에 커피 이름 저장
    ////    dto.setFileName(fileNames); // (주의) dto.setFileName()이 List를 받도록 설계되어 있어야 가능한 코드
    //
    //    model.addAttribute("fileName", fileNames); // View에 여러 파일명을 전달
    //    model.addAttribute("coffeeName",coffeeName); // View에 커피 이름 전달
    //    return "uploadResult"; // 결과 페이지 반환
    //}
}