package com.office.notfound.review.model.dao;

import com.office.notfound.review.model.dto.OfficeReviewDTO;
import com.office.notfound.review.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<ReviewDTO> selectAllReviewList();

    List<OfficeReviewDTO> selectOfficeReviewList();

    List<OfficeReviewDTO> findOfficeReview(int storeCode);

    void registNewReview(ReviewDTO newReview);

    List<ReviewDTO> selectReviewsByMemberId(String memberId);
}
