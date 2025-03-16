package com.office.notfound.inquiry.model.dao;

import com.office.notfound.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {
    List<InquiryDTO> selectAllInquiryList();

    InquiryDTO selectInquiryByCode(int code);
}
