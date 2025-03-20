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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/store")
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
        return "samusil/admin/officecreate";

    }

//    // 사무실 등록 페이지
//    @PostMapping("samusil/admin/officecreate")
//    public String adminOfficeCreate(@ModelAttribute OfficeDTO office,
//                                    @RequestParam("officeThumbnailUrl") MultipartFile officeThumbnailUrl,
//                                    RedirectAttributes rAttr) {
//        try {
//            officeService.insertOffice(office, officeThumbnailUrl);
//            rAttr.addFlashAttribute("message", "사무실 등록을 성공하였습니다.");
//            return "redirect:/samusil/admin/officemanage";
//        } catch (Exception e) {
//            rAttr.addFlashAttribute("error", "사무실 등록을 실패하였습니다.");
//            return "redirect:/samusil/admin/officecreate";
//        }
//    }

//    // 지점별 사무실 수정 페이지 넘어가기
//    @PostMapping("samusil/admin/officeedit/{storeCode}/{officeCode}")
//    public String adminOfficeEditPage(@PathVariable("storeCode") int storeCode,
//                                      @PathVariable("officeCode") int officeCode,
//                                      Model model) {
//
//        OfficeDTO office = officeService.findOfficeByStore(storeCode, officeCode);
//
//        model.addAttribute("office", office);
//
//        return "samusil/admin/officeedit";
//    }
//
//    // 지점별 사무실 수정 정보 저장하기
//    @PostMapping("samusil/admin/officeedit/{storeCode}/{officeCode}")
//    public String adminOfficeEdit(@PathVariable("storeCode") int storeCode,
//                                  @PathVariable("officeCode") int officeCode,
//                                  @ModelAttribute StoreDTO store,
//                                  @ModelAttribute OfficeDTO office,
//                                  RedirectAttributes rAttr) {
//
//        try {
//            // 객체타입 중 하나인 String이라 null과 비교 가능
//            if (office.getOfficeType() == null) {
//                throw new IllegalArgumentException("필수 입력 데이터가 누락되었습니다.");
//            }
//            // 기본 원시형이라 0이랑 비교
//            if(office.getOfficeNum() == 0 || office.getOfficePrice() == 0) {
//                throw new IllegalArgumentException("필수 입력 데이터가 누락되었습니다.");
//            }
//
//            store.setStoreCode(storeCode);
//            office.setOfficeCode(officeCode);
//            officeService.updateOffice(office);
//            rAttr.addFlashAttribute("message", "사무실 수정를 성공하였습니다.");
//            return "redirect:/samusil/admin/officemanage";
//        } catch (Exception e) {
//            rAttr.addFlashAttribute("error", "사무실 수정를 실패하였습니다.");
//            return "redirect:/samusil/admin/officeedit";
//        }
//
//    }

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
            return "redirect:/samusil/admin/officemanage/";
    }

    @PostMapping("/samusil/admin/officemanage/{storeode}")
    public String deleteOffice(@PathVariable("officeCode") int officeCode, RedirectAttributes rAttr) {
        try {
            storeService.deleteStore(officeCode);
            rAttr.addFlashAttribute("message", "사무실 삭제를 성공하였습니다.");
        } catch (Exception e) {
            rAttr.addFlashAttribute("errorMessage", "삭제 중 오류가 발생하였습니다. " + e.getMessage());
        }
        return "redirect:/samusil/admin/officemanage";
    }

}
