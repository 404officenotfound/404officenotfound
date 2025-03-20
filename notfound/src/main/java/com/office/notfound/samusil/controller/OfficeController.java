package com.office.notfound.samusil.controller;

import com.office.notfound.samusil.model.dto.OfficeDTO;
import com.office.notfound.samusil.model.service.OfficeService;
import com.office.notfound.store.model.dto.StoreDTO;
import com.office.notfound.store.model.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("/store/detailstore")
    public String officeList(@RequestParam("storeCode") int storeCode, Model model) {

        StoreDTO store = storeService.findStoreByCode(storeCode);

        List<OfficeDTO> officeList = officeService.findAllOffices(storeCode);

        model.addAttribute("store", store);
        model.addAttribute("officeList", officeList);

        // 반환할 뷰 aka 보여줄 html파일 작성
        return "/store/detailstore";
    }

    @GetMapping("/store/detailoffice/{storeCode}/{officeCode}")
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

    @GetMapping("/samusil/admin/officemanage/{storeCode}")
    public String adminOfficeList(@PathVariable("storeCode") int storeCode, Model model) {
        StoreDTO store = storeService.findStoreByCode(storeCode);
        List<OfficeDTO> officeList = officeService.findAllOffices(storeCode);

        model.addAttribute("officeList", officeList);
        model.addAttribute("store", store);

        // 반환할 뷰 aka 보여줄 html파일 작성
        return "samusil/admin/officemanage";
    }

    // 관리자용 사무실 등록 페이지
    @GetMapping("/samusil/admin/officecreate")
    public String adminOfficeCreatePage() {
        return "/samusil/admin/officecreate";
    }

    // 지점별 사무실 삭제
    @PostMapping("samusil/admin/officemanage/{storeCode}")
    public String adminOfficeDelete(@PathVariable("officeCode") int officeCode,
                                    RedirectAttributes rAttr) {
        try {
            officeService.deleteOffice(officeCode);
            rAttr.addFlashAttribute("message", "사무실 삭제를 성공하였습니다.");
        } catch (Exception e) {
            rAttr.addFlashAttribute("errorMessage", "사무실 삭제를 실패하였습니다." + e.getMessage());
        }
        return "redirect:.store/samusil/admin/officemanage";
    }
}
