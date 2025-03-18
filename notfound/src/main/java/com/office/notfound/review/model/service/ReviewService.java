package com.office.notfound.review.model.service;

import com.office.notfound.common.util.FileUploadUtils;
import com.office.notfound.review.model.dao.ReviewMapper;
import com.office.notfound.review.model.dto.OfficeReviewDTO;
import com.office.notfound.review.model.dto.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    @Value("/build/resources/main/static/img/review")
    private String IMAGE_DIR;

    @Value("/img/review")
    private String IMAGE_URL;


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
    public void registNewReview(ReviewDTO newReview, MultipartFile reviewThumbnail) throws Exception {

        // 이미지 저장
        if (reviewThumbnail != null && !reviewThumbnail.isEmpty()) {

            String imageName = UUID.randomUUID().toString().replace("-", "");
            String replaceFileName = FileUploadUtils.saveReviewFile(IMAGE_DIR, imageName, reviewThumbnail);

            newReview.setReviewImage(replaceFileName);

            // 서비스에서 파일 저장 후 경로 확인
            System.out.println("파일 저장 경로 확인: " + replaceFileName);

//            System.out.println("리뷰서비스 imageName-----------------> = " + imageName);
//            System.out.println("리뷰서비스 newReview-----------------> = " + newReview);
        } else {
            newReview.setReviewImage(null);
        }

        log.info("새 리뷰 등록: {}", newReview);

        reviewMapper.registNewReview(newReview);
    }

    public List<ReviewDTO> selectReviewsByMemberId(String memberId) {

        System.out.println("reviewService-------------memberId = " + memberId);

        return reviewMapper.selectReviewsByMemberId(memberId);
    }
}
