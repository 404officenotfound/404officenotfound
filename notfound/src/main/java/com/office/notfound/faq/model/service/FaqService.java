package com.office.notfound.faq.model.service;

import com.office.notfound.faq.model.dao.FaqMapper;
import com.office.notfound.faq.model.dto.FaqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {

    public final FaqMapper faqMapper;

    @Autowired
    public FaqService(FaqMapper faqMapper) {
        this.faqMapper = faqMapper;
    }

    public List<FaqDTO> selectAllFaqList() {
        return faqMapper.selectAllFaqList();
    }
}
