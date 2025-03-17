package com.office.notfound.faq.model.dao;

import com.office.notfound.faq.model.dto.FaqDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqMapper {
    List<FaqDTO> selectAllFaqList();
}
