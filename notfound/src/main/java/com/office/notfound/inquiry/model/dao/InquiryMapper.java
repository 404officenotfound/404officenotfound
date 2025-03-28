package com.office.notfound.inquiry.model.dao;

import com.office.notfound.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {


    List<InquiryDTO> selectAllInquiryList();

    List<InquiryDTO> selectMyInquiryList(int memberCode);

    InquiryDTO selectInquiryByCode(int inquiryCode);

    void registNewInquiry(InquiryDTO newInquiry);

    InquiryDTO findMyInquiryByCode(int inquiryCode);

    void updateReview(InquiryDTO myInquiry);

    void deleteInquiry(int inquiryCode);

}
