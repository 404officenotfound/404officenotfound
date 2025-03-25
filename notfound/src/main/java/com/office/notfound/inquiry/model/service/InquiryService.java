package com.office.notfound.inquiry.model.service;

import com.office.notfound.inquiry.model.dao.InquiryMapper;
import com.office.notfound.inquiry.model.dto.InquiryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryMapper inquiryMapper;
    private static final Logger log = LoggerFactory.getLogger(InquiryService.class);

    @Autowired
    public InquiryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    public InquiryDTO findMyInquiryByCode(int code) {
        InquiryDTO myInquiry = inquiryMapper.findMyInquiryByCode(code);

        return myInquiry;
    }

    // 모든 문의 조회 (관리자)
    public List<InquiryDTO> selectAllInquiryList() {

        return inquiryMapper.selectAllInquiryList();
    }

    // 특정 사용자의 문의 조회
    public List<InquiryDTO> selectMyInquiryList(int memberCode) {
        return inquiryMapper.selectMyInquiryList(memberCode);
    }

    // 문의 상세 페이지
    public InquiryDTO selectInquiryByCode(int inquiryCode) {
        return inquiryMapper.selectInquiryByCode(inquiryCode);
    }

    // 문의글 등록
    @Transactional
    public void registNewInquiry(InquiryDTO newInquiry) {

        log.info("새 리뷰 등록: {}", newInquiry);

        inquiryMapper.registNewInquiry(newInquiry);
    }


    // 문의글 수정
    @Transactional
    public void updateMyInquiry(InquiryDTO myInquiry) {

        inquiryMapper.updateReview(myInquiry);
    }

    // 문의글 삭제
    public void deleteInquiry(int inquiryCode) {
        inquiryMapper.deleteInquiry(inquiryCode);
    }
}
