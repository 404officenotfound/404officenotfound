package com.office.notfound.inquiry.controller;

import com.office.notfound.inquiry.model.dto.InquiryDTO;
import com.office.notfound.inquiry.model.service.InquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/list")
    public String selectAllInquiryList(Model model) {

        List<InquiryDTO> inquiryList = inquiryService.selectAllInquiryList();

        model.addAttribute("inquiryList", inquiryList);
//        System.out.println("inquiryList " + inquiryList);

        return "inquiry/inquiry";
    }

    @GetMapping("/detail/{code}")
    public String selectInquiryDetail(@PathVariable("code") int code, Model model) {

        InquiryDTO inquiry = inquiryService.selectInquiryByCode(code);

        model.addAttribute("inquiry", inquiry);
//        System.out.println("inquiry " + inquiry);

        return "inquiry/inquiryDetail";
    }
}
