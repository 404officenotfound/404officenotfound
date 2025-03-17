package com.office.notfound.store.controller;

import com.office.notfound.review.model.dto.OfficeReviewDTO;
import com.office.notfound.review.model.service.ReviewService;
import com.office.notfound.samusil.model.dto.OfficeDTO;
import com.office.notfound.samusil.model.service.OfficeService;
import com.office.notfound.store.model.dto.StoreDTO;
import com.office.notfound.store.model.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class StoreController {

    private final StoreService storeService;
    private final OfficeService officeService;
    private final ReviewService reviewService;

    @Autowired
    public StoreController(StoreService storeService, OfficeService officeService, ReviewService reviewService) {
        this.storeService = storeService;
        this.officeService = officeService;
        this.reviewService = reviewService;
    }

    // 전체 지점 조회
    @GetMapping("/store/storelist")
    public String storeList(Model model) {

        List<StoreDTO> storeList = storeService.findAllStores();

        model.addAttribute("storeList", storeList);

        return "/store/storelist";
    }

    // 특정 지점 상세 조회 후 해당 사무실 리스트 전체 조회
    @GetMapping("/store/detailstore/{storeCode}")
    public String storeDetail(@PathVariable("storeCode") int storeCode, Model model) {
        // storeCode에 해당하는 매장 정보 조회
        StoreDTO store = storeService.findStoreByCode(storeCode);

        List<OfficeDTO> officeList = officeService.findAllOffices(storeCode);
        // 사무실 상세 리스트 내 리뷰 조회용
        List<OfficeReviewDTO> FindOfficeReview = reviewService.findOfficeReview(storeCode);


        // 모델에 해당 매장 정보를 담아 상세 페이지를 반환
        model.addAttribute("store", store);
        model.addAttribute("officeList", officeList);
        model.addAttribute("FindOfficeReview", FindOfficeReview);


//        System.out.println("officeList = " + officeList);
//        System.out.println("FindOfficeReview = " + FindOfficeReview);

        return "store/detailstore";
    }

    // 관리자페이지>매장관리의 디폴트화면은 전체지점리스트
    // 관리자용 상품 목록
    @GetMapping("/store/admin/storemanage")
    public ModelAndView adminStoreList(ModelAndView mv) {

        List<StoreDTO> stores = storeService.findAllStores();

        mv.addObject("stores", stores);
        mv.setViewName("store/admin/storemanage");

        return mv;
    }

    // 관리자용 상품 등록 페이지
    @GetMapping("store/admin/storecreate")
    public String adminStoreCreatePage() {
        return "store/admin/storecreate";
    }

    // 관리자용 상품 등록 처리
    @PostMapping("store/admin/storecreate")
    public String adminStoreCreate(@ModelAttribute StoreDTO store,
                                   @RequestParam("storeImage") MultipartFile storeImage,
                                   RedirectAttributes rttr) {
        try {
            storeService.createStore(store, storeImage);
            rttr.addFlashAttribute("message", "새 지점 등록을 성공하였습니다.");
            // 지점 등록 성공 후 이동하는 페이지는 디폴트
            return "redirect:/store/admin/storemanage";
        } catch (Exception e) {
            rttr.addFlashAttribute("message", "새 지점 등록에 실패했습니다: " + e.getMessage());
            return "redirect:/store/admin/storecreate";
        }
    }

    // 시(city) 정보 조회
    @GetMapping("/store/storeregion/cities")
    @ResponseBody
    public List<String> getStoreCities() {
        return storeService.getDistinctCities();
    }

    // 시 선택 시 해당 구/군 정보 조회
    @GetMapping("/store/storeregion/gu")
    @ResponseBody
    public List<String> getGuByCity(@RequestParam("city") String city) {
        return storeService.getGuByCity(city);
    }
}
