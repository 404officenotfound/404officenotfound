package com.office.notfound.review.model.service;

import com.office.notfound.review.model.dao.ReviewMapper;
import com.office.notfound.review.model.dto.OfficeReviewDTO;
import com.office.notfound.review.model.dto.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;


    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);



    @Autowired
    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public List<ReviewDTO> selectAllReviewList() {

        return reviewMapper.selectAllReviewList();

    }

    public List<OfficeReviewDTO> selectOfficeReviewList() {

        return reviewMapper.selectOfficeReviewList();
    }

    public List<OfficeReviewDTO> findOfficeReview(int storeCode) {

        return reviewMapper.findOfficeReview(storeCode);
    }

    @Transactional
    public void registNewReview(ReviewDTO newReview) {

        log.info("새 리뷰 등록: {}", newReview);

        reviewMapper.registNewReview(newReview);
    }
}
