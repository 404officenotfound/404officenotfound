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

    public List<InquiryDTO> selectAllInquiryList() {

        return inquiryMapper.selectAllInquiryList();
    }

    public InquiryDTO selectInquiryByCode(int code) {
        return inquiryMapper.selectInquiryByCode(code);
    }
}
