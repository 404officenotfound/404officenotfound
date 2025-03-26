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

    // 리뷰 전체 조회 (관리자용)
    @GetMapping("/list")
    public String selectAllReviewList( Model model) {

        List<ReviewDTO> reviewList = reviewService.selectAllReviewList();

        model.addAttribute("reviewList", reviewList);

        return "review/review";
    }


    // 리뷰가 등록된 사무실 리스트 전체 조회용 핸들러
    @GetMapping("/officelist")
    public String selectOfficeReviewList( Model model) {

        List<OfficeReviewDTO> officeReviewList = reviewService.selectOfficeReviewList();

        model.addAttribute("officeReview", officeReviewList);

        return "review/officeReview";
    }

    // 나의 리뷰 조회 (일반회원용)
    @GetMapping("/my-reviews")
    public String selectMyReviews(@AuthenticationPrincipal MemberDTO member
            , Model model) {

        // 로그인한 멤버 코드로 리뷰 조회
        List<ReviewDTO> myReviews = reviewService.selectReviewsByMemberCode(member.getMemberCode());

        model.addAttribute("member", member);
        model.addAttribute("reviewList", myReviews);

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
            // 로그인한 회원의 ID 및 memberCode 넣기
            newReview.setMemberId(member.getMemberId());
            // 로그인한 회원번호 찾아서 새 리뷰 객체에 넣기
            newReview.setMemberCode(member.getMemberCode());
            // 리뷰 등록 전 현재 날짜로 reviewRegistDate 설정
            newReview.setReviewDate(LocalDate.now());

            newReview.setPaymentCode(1);        // payment_code를 1로 설정

//            System.out.println("newReview.getReviewDate() = " + newReview.getReviewDate());

            reviewService.registNewReview(newReview, reviewThumbnail);

            logger.info("Locale : {}", locale);
            logger.info("LocalDate.now() : {}", LocalDate.now());

            rAttr.addFlashAttribute("message", "새 리뷰 등록을 성공하였습니다.");

            if (newReview.getMemberCode() == 1) {
                return "redirect:/review/list";
            } else {
                return "redirect:/review/my-reviews";     // 리뷰 목록으로 리다이렉트

            }

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

        return mv;
    }

    // 리뷰 수정 핸들러
    @PostMapping("/edit/{reviewCode}")
    public String userReviewEdit(@PathVariable int reviewCode,
                                 @ModelAttribute ReviewDTO myReview,
                                 @AuthenticationPrincipal MemberDTO member,
                                 @RequestParam(required = false) MultipartFile reviewThumbnail,
                                 RedirectAttributes rAttr) {

        try {
            myReview.setReviewCode(reviewCode);
            // 로그인의 memberID 넣기
            myReview.setMemberId(member.getMemberId());
            // 로그인한 회원번호 찾아서 새 리뷰 객체에 넣기
            myReview.setMemberCode(member.getMemberCode());
            myReview.setPaymentCode(1);        // payment_code를 1로 설정

            reviewService.updateMyReview(myReview, reviewThumbnail);

            rAttr.addFlashAttribute("message", "리뷰가 수정되었습니다.");


            if (myReview.getMemberCode() == 1) {
                return "redirect:/review/list";
            } else {
                return "redirect:/review/my-reviews";     // 리뷰 목록으로 리다이렉트

            }

        } catch (Exception e) {
            e.printStackTrace();
            rAttr.addFlashAttribute("message", "리뷰 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/review/edit/" + reviewCode;
        }
    }


    // 리뷰 삭제 핸들러
    @PostMapping("/delete/{reviewCode}")
    public String userReviewDelete(@PathVariable int reviewCode,
                                   @AuthenticationPrincipal MemberDTO member,
                                     RedirectAttributes rAttr) {

        try {
            reviewService.deleteReview(reviewCode);
            rAttr.addFlashAttribute("message", "리뷰가 삭제되었습니다.");

            if (member.getMemberCode() == 1) {
                return "redirect:/review/list";
            } else {
                return "redirect:/review/my-reviews";     // 리뷰 목록으로 리다이렉트

            }

        } catch (Exception e) {
            e.printStackTrace();
            rAttr.addFlashAttribute("message", "리뷰가 실패했습니다: " + e.getMessage());
            return "redirect:/review/my-reviews";
        }
    }

}