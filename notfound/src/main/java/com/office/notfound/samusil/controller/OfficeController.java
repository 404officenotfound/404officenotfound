package com.office.notfound.samusil.controller;

import com.office.notfound.samusil.model.dto.OfficeDTO;
import com.office.notfound.samusil.model.service.OfficeService;
import com.office.notfound.store.model.dto.StoreDTO;
import com.office.notfound.store.model.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OfficeController {

    private final OfficeService officeService;
    private final StoreService storeService;

    @Autowired
    public OfficeController(OfficeService officeService, StoreService storeService) {
        this.officeService = officeService;
        this.storeService = storeService;
    }

    // 사무실 전체 조회
    @GetMapping("/detailstore")
    public String officeList(@RequestParam("storeCode") int storeCode, Model model) {

        List<OfficeDTO> officeList = officeService.findAllOffices(storeCode);

        model.addAttribute("officeList", officeList);

        // 반환할 뷰 aka 보여줄 html파일 작성
        return "/detailstore";
    }

    @GetMapping("detailoffice/{storeCode}/{officeCode}")
    public String findOfficeDetail(@PathVariable("storeCode") int storeCode,
                                   @PathVariable("officeCode") int officeCode,
                                   Model model) {

        // Store 정보 조회 (단일 객체 반환)
        StoreDTO store = storeService.findStoreByCode(storeCode);
        // Office 정보 조회 (단일 객체 반환)
        OfficeDTO office = officeService.findOfficeDetail(officeCode);

        // office가 null인지 확인 (오류 방지)
        if (office == null) {
            System.out.println("❌ Office 객체가 null입니다. officeCode: " + officeCode);
            model.addAttribute("errorMessage", "해당 사무실 정보를 찾을 수 없습니다.");
            return "error-page"; // 에러 페이지로 이동
        }

        // Debug 로그 추가 (콘솔에서 데이터 확인)
        System.out.println("✅ [DEBUG] storeCode: " + storeCode + ", officeCode: " + officeCode);
        System.out.println("✅ [DEBUG] 조회된 Office 정보: " + office);

        // 모델에 데이터를 추가
        model.addAttribute("store", store);
        model.addAttribute("office", office);

        // 예약 등록 페이지로 리다이렉트
        return "redirect:/reservation/register?storeCode=" + storeCode + "&officeCode=" + officeCode;
    }
}
