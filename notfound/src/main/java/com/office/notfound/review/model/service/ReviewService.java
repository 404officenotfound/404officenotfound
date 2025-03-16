package com.office.notfound.review.model.service;

import com.office.notfound.review.model.dao.ReviewMapper;
import com.office.notfound.review.model.dto.OfficeReviewDTO;
import com.office.notfound.review.model.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

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
}
