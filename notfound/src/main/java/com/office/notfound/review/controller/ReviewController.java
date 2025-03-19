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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private static final Logger logger = LogManager.getLogger(ReviewController.class);

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService, MessageSource messageSource) {

        this.reviewService = reviewService;
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
//        System.out.println("officeReviewList--------------> " + officeReviewList);

        return "review/officeReview";
    }

    // 나의 리뷰 조회
    @GetMapping("/my-reviews")
    public String selectMyReviews(@AuthenticationPrincipal MemberDTO member
            , Model model) {

//        System.out.println("member확인-------------------> " + member);

        // 현재 로그인한 사용자의 ID 확인
        System.out.println("로그인한 회원 ID: " + member.getMemberId());

        // 로그인한 회원 ID로 리뷰 조회
        List<ReviewDTO> myReviews = reviewService.selectReviewsByMemberId(member.getMemberId());

        model.addAttribute("member", member);
        model.addAttribute("reviewList", myReviews);
//        System.out.println("myReviews--------------------> = " + myReviews);

        return "review/my-reviews";
    }


    // 리뷰 등록 페이지
    @GetMapping("regist")
    public void registPage() {}

    // 리뷰 등록
    @PostMapping("regist")
    public String registReview(@ModelAttribute ReviewDTO newReview,
                               @AuthenticationPrincipal MemberDTO member,
                               @RequestParam("reviewThumbnail") MultipartFile reviewThumbnail,
                               RedirectAttributes rAttr,
                               Locale locale) {
        try {
            // 로그인의 memberID 넣기
            newReview.setMemberId(member.getMemberId());
            // 로그인한 회원번호 찾아서 새 리뷰 객체에 넣기
            newReview.setMemberCode(member.getMemberCode());
            // 리뷰 등록 전 현재 날짜로 reviewRegistDate 설정
//            newReview.setReviewDate(LocalDate.now());

            newReview.setPaymentCode(1);        // payment_code를 1로 설정

            System.out.println("newReview.getReviewDate() = " + newReview.getReviewDate());

            reviewService.registNewReview(newReview, reviewThumbnail);

            logger.info("Locale : {}", locale);

            rAttr.addFlashAttribute("message", "새 리뷰 등록을 성공하였습니다.");
            System.out.println("newReview222222222------------> " + newReview);

            return "redirect:/review/my-reviews";     // 리뷰 목록으로 리다이렉트

        } catch (IllegalArgumentException e) {
            rAttr.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/review/regist";
        }   catch (Exception e) {
            e.printStackTrace();
            rAttr.addFlashAttribute("errorMessage", "등록 실패: " + e.getMessage());
            return "redirect:/review/regist";
        }

    }


    // 리뷰 수정 페이지
    @GetMapping("/edit/{reviewCode}")
    public ModelAndView userReviewEditPage(@PathVariable int reviewCode,
                                             ModelAndView mv) {

        ReviewDTO myReview = reviewService.findMyReviewByCode(reviewCode);

        mv.addObject("myReview", myReview);
        mv.setViewName("review/edit");
        System.out.println("myReview----------------> = " + myReview);
        System.out.println("mv-------------------->" + mv);
        System.out.println("reviewCode------------->" + reviewCode);

        return mv;
    }

    // 리뷰 수정 핸들러
    @PostMapping("/edit/{reviewCode}")
    public String userReviewEdit(@PathVariable int reviewCode,
                                   @ModelAttribute ReviewDTO myReview,
                                   @RequestParam(required = false) MultipartFile reviewThumbnail,
                                   RedirectAttributes rAttr) {
        try {
            myReview.setReviewCode(reviewCode);
            System.out.println("myReview--------------> = " + myReview);

            reviewService.updateMyReview(myReview, reviewThumbnail);

            rAttr.addFlashAttribute("message", "리뷰가 수정되었습니다.");
            return "redirect:/review/my-reviews";
        } catch (Exception e) {
            rAttr.addFlashAttribute("message", "리뷰 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/review/edit/" + reviewCode;
        }
    }

}
