package com.office.notfound.member.controller;


import com.office.notfound.member.model.dto.SignupDTO;
import com.office.notfound.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signup(){
        return "member/signup";
    }

    @PostMapping
    public ModelAndView signup(ModelAndView mv,
                               SignupDTO signupDTO,
                               RedirectAttributes rAttr){

        Integer result = memberService.regist(signupDTO);

        /* 비즈니스 로직 성공 여부에 따라서 Dispatcher Servlet에 반환할 View와 Model을 세팅. */
        String message = null;

        if(result == null){
            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
            System.out.println("message = " + message);
            mv.setViewName("redirect:/member/signup");
        }else if(result == 0){
            message = "회원가입에 실패했습니다. 다시 시도해주세요.";
            System.out.println("message = " + message);
            mv.setViewName("redirect:/member/signup");
        }else if(result >= 1){
            message = "회원 가입이 성공적으로 완료되었습니다.";
            System.out.println("message = " + message);
            mv.setViewName("redirect:/");
        }else{
            message = "알 수 없는 오류가 발생했습니다. 관리자에게 문의하세요";
            System.out.println("message = " + message);
            mv.setViewName("redirect:/member/signup");
        }

        rAttr.addFlashAttribute("message", message); // 리다이렉트
        return mv;
    }
}
