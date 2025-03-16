package com.office.notfound.review.controller;

import com.office.notfound.review.model.dto.OfficeReviewDTO;
import com.office.notfound.review.model.dto.ReviewDTO;
import com.office.notfound.review.model.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

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

        return "review/officeReview";
    }

}
