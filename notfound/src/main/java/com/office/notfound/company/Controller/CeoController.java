package com.office.notfound.company.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CeoController {

    @GetMapping("/ceo")
    public String introduce() {
        return "company/ceo";
    }
}
