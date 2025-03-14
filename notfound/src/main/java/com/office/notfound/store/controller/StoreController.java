package com.office.notfound.store.controller;


import com.office.notfound.store.model.dto.StoreDTO;
import com.office.notfound.store.model.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // 전체 지점 조회
    @GetMapping("/storelist")
    public String storeList(Model model) {

        List<StoreDTO> storeList  = storeService.findAllStores();

        model.addAttribute("storeList", storeList);

        return "/store/storelist";
    }

//    // 지역별 지점 조회 : 시도를 정하고 시군구를 정한다.
//    @GetMapping("storeregion/{storeCity}")
//    @ResponseBody
//    public String getStoreRegion(@PathVariable String storeCity, Model model) {
//
//        List<StoreDTO> storeRegion = storeService.findStoresByCity(storeCity);
//
//        model.addAttribute("storeRegion", storeRegion);
//
//        return "store/storeregion";
//    }
//
//    @GetMapping()
//    @ResponseBody
//    public List<StoreDTO> getStoreCityAndGu(@PathVariable String storeCity) {
//        List<StoreDTO> storeCityAndGuList = storeService.findStoresByCity(storeCity);
//        return storeCityAndGuList;
//    }

//    @GetMapping("/storeregion")
//    public String showRegionForm(Model model) {
//        // 데이터베이스에서 storeCity 목록 가져오기 (예제 코드)
//        List<String> cityList = StoreService.findStoresByCity();
//
////        if (cityList == null) {
////            cityList = Collections.emptyList();
////        }
//
//        model.addAttribute("cities", cityList != null ? cityList : Collections.emptyList());
//
//        // view에서 storeregion.html을 보여주기로 결정함.
//        return "storeregion";
//    }
//
//    @GetMapping("/gu")
//    @ResponseBody
//    public List<String> getGuByCity(@RequestParam String storeCity) {
//        return storeService.getGuByCity(storeCity);
//    }

//    // 상세 지점 조회
//    @GetMapping("/store/detailstore/{storeCode}")
//    public ModelAndView storeDetail(@PathVariable int storeCode, ModelAndView mv) {
//
//        mv.addObject("store", storeService.findStoreByCode(storeCode));
//        mv.setViewName("store/detailstore");
//
//        return mv;
//    }
}
