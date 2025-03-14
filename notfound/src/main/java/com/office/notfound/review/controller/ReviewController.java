package com.office.notfound.review.controller;

import com.office.notfound.review.model.dto.OfficeReviewDTO;
import com.office.notfound.review.model.dto.ReviewDTO;
import com.office.notfound.review.model.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/review")
public class ReviewController {

//    private static final Logger logger = Logger.getLogger(ReviewController.class);

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


    @GetMapping("/officelist")
    public String selectOfficeReviewList( Model model) {

        List<OfficeReviewDTO> officeReviewList = reviewService.selectOfficeReviewList();

        model.addAttribute("officeReview", officeReviewList);
        System.out.println("officeReviewList: " + officeReviewList);

        return "review/officeReview";
    }


/*    public String selectOfficeReviewList(@PathVariable("code") int code,
                                         Model model) {

        // 한 사무실에 여러 개의 리뷰가 있기 때문에 List 타입으로 사용
        List<OfficeReviewDTO> officeReviewList = reviewService.selectOfficeReviewList(code);

        model.addAttribute("officeReview", officeReviewList);
        System.out.println("list/detail/code 찍히나-----"+ model.addAttribute("officeReviewList"));

        return "officeReview";
    }*/
}
