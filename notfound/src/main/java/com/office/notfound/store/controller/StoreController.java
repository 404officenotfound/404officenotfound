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

        List<StoreDTO> storeList  = storeService.findAllStores();

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

}
