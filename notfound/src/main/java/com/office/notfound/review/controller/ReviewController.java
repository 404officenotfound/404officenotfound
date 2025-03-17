package com.office.notfound.review.controller;

import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.review.model.dto.OfficeReviewDTO;
import com.office.notfound.review.model.dto.ReviewDTO;
import com.office.notfound.review.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private static final Logger logger = LogManager.getLogger(ReviewController.class);

    private final ReviewService reviewService;
    private final MessageSource messageSource;

    @Autowired
    public ReviewController(ReviewService reviewService, MessageSource messageSource) {

        this.reviewService = reviewService;
        this.messageSource = messageSource;
    }

    // 리뷰 전체 조회
    @GetMapping("/list")
    public String selectAllReviewList( Model model) {

        List<ReviewDTO> reviewList = reviewService.selectAllReviewList();

        model.addAttribute("reviewList", reviewList);

        return "review/review";
    }


    // 리뷰가 등록된 사무실 리스트 전체 조회용 컨트롤러
    @GetMapping("/officelist")
    public String selectOfficeReviewList( Model model) {

        List<OfficeReviewDTO> officeReviewList = reviewService.selectOfficeReviewList();

        model.addAttribute("officeReview", officeReviewList);
        System.out.println("officeReviewList--------------> " + officeReviewList);

        return "review/officeReview";
    }


    // 리뷰 등록 페이지
    @GetMapping("regist")
    public void registPage() {}

/*    // 평점 컨트롤러 이걸로 사용하나?
    @GetMapping(value="category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {

        System.out.println("JavaScript 내장 함수인 fetch 비동기 함수 도착!");
        return menuService.findAllCategory();
    }*/

    @PostMapping("regist")
    public String registReview(@ModelAttribute ReviewDTO newReview,
                               @AuthenticationPrincipal MemberDTO member,
                               RedirectAttributes rAttr,
                               Locale locale) {
        try {
            // 로그인의 memberID 넣기
            newReview.setMemberId(member.getMemberId());
            // 로그인한 회원번호 찾아서 새 리뷰 객체에 넣기
            newReview.setMemberCode(member.getMemberCode());
            // 리뷰 등록 전 현재 날짜로 reviewRegistDate 설정
            newReview.setReviewDate(LocalDate.now());

            System.out.println("newReview11111-------------------> = " + newReview);
            System.out.println("member------------> = " + member);

            reviewService.registNewReview(newReview);

            logger.info("Locale : {}", locale);

            rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registReview", null, locale));
//            System.out.println("newReview222222222------------> " + newReview);

            return "redirect:/review/list";     // 리뷰 목록으로 리다이렉트

        } catch (Exception e) {
            rAttr.addFlashAttribute("errorMessage", "등록 실패: " + e.getMessage());
        }

        return "redirect:/review/regist";
    }
}
