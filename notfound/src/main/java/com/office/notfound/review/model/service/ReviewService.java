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

        } else {
            newReview.setReviewImage(null);
        }

        log.info("새 리뷰 등록: {}", newReview);

        reviewMapper.registNewReview(newReview);
    }

    public List<ReviewDTO> selectReviewsByMemberCode(int memberCode) {

        return reviewMapper.selectReviewsByMemberCode(memberCode);
    }

    public ReviewDTO findMyReviewByCode(int reviewCode) {
        ReviewDTO myReview = reviewMapper.findMyReviewByCode(reviewCode);

        if(myReview != null) {
            // 이미지 URL 완성
            myReview.setReviewImage(myReview.getReviewImage());
        }

        System.out.println("myReview.getReviewImage() = " + myReview.getReviewImage());


        return myReview;
    }

    @Transactional
    public void updateMyReview(ReviewDTO myReview, MultipartFile reviewThumbnail) throws Exception {

        // 기존 리뷰 정보를 조회하여 이미지 URL 가져오기
        ReviewDTO existingReview = reviewMapper.findMyReviewByCode(myReview.getReviewCode());
        String originalImageUrl = existingReview.getReviewImage();
        System.out.println("기존 이미지 URL: " + originalImageUrl);  // 디버깅용

        try {
            // 이미지가 새로 업로드된 경우
            if (reviewThumbnail != null && !reviewThumbnail.isEmpty()) {

                // 기존 이미지가 있다면 먼저 삭제
                if (originalImageUrl != null && !originalImageUrl.isEmpty()) {

//                    FileUploadUtils.deleteReviewFile();
                }

                // 새 이미지 저장
                String imageName = UUID.randomUUID().toString().replace("-", "");
                String newImageUrl = FileUploadUtils.saveReviewFile(IMAGE_DIR, imageName, reviewThumbnail);
                System.out.println("새 이미지 저장됨: " + newImageUrl);


                // 리뷰 정보에 새 이미지 URL 설정
                myReview.setReviewImage(newImageUrl);
            } else {
                // 이미지가 변경되지 않은 경우 기존 이미지 URL 유지
                myReview.setReviewImage(originalImageUrl);
            }

            // 리뷰 정보 업데이트
            reviewMapper.updateReview(myReview);

            }   catch (Exception e) {
            e.printStackTrace();
            // 새 이미지 저장 후 업데이트 실패 시, 새로 저장된 이미지도 삭제
            if (reviewThumbnail != null && !reviewThumbnail.isEmpty() &&
                    myReview.getReviewImage() != null &&
                    originalImageUrl != null &&
                    !myReview.getReviewImage().equals(originalImageUrl)) {
                /* 이미지 삭제 기능 직접 구현해보세요~ */
//                FileUploadUtils.deleteReviewFile();
            }
            throw e;
        }

    }

    public void deleteReview(int reviewCode) throws Exception {

        // 리뷰 이미지 정보 조회
        ReviewDTO review = reviewMapper.findMyReviewByCode(reviewCode);

        if (review == null) {
            throw new IllegalArgumentException("리뷰가 존재하지 않습니다.");
        }

        // 이미지 파일 삭제
        if (review.getReviewImage() != null) {
            /* 이미지 삭제 기능 직접 구현해보세요~ */
//            FileUploadUtils.deleteFile();
        }

        // 상품 정보 삭제
        reviewMapper.deleteReview(reviewCode);
    }
}
