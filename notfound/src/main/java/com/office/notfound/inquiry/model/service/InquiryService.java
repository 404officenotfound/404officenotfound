package com.office.notfound.inquiry.model.service;

import com.office.notfound.inquiry.model.dao.InquiryMapper;
import com.office.notfound.inquiry.model.dto.InquiryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryMapper inquiryMapper;

    @Autowired
    public InquiryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
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
    public InquiryDTO selectInquiryByCode(int code) {
        return inquiryMapper.selectInquiryByCode(code);
    }

}
