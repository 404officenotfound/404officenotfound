package com.office.notfound.faq.controller;

import com.office.notfound.faq.model.dto.FaqDTO;
import com.office.notfound.faq.model.service.FaqService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/faq")
public class FaqController {

    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/list")
    public String selectAllFaqList(Model model) {

        List<FaqDTO> faq = faqService.selectAllFaqList();
        model.addAttribute("faq", faq);
//        System.out.println("faq------------> " + faq);

        return "faq/faq";
    }
}
