package com.office.notfound.samusil.controller;

import com.office.notfound.samusil.model.dto.OfficeDTO;
import com.office.notfound.samusil.model.service.OfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    // 사무실 전체 조회
    @GetMapping("/detailstore")
    public String officeList(@RequestParam("storeCode") int storeCode, Model model) {

        List<OfficeDTO> officeList = officeService.findAllOffices(storeCode);

        model.addAttribute("officeList", officeList);

        // 반환할 뷰 aka 보여줄 html파일 작성
        return "/detailstore";
    }

    @GetMapping("detailoffice/{officeCode}")
    public String findOfficeDetail(@PathVariable("officeCode") int officeCode, Model model) {

        OfficeDTO office = officeService.findOfficeDetail(officeCode);

        model.addAttribute("office", office);

        return "store/detailoffice";
    }
}
