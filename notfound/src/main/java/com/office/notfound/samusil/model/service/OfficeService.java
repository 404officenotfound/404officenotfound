package com.office.notfound.samusil.model.service;

import com.office.notfound.samusil.model.dao.OfficeMapper;
import com.office.notfound.samusil.model.dto.OfficeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    private final OfficeMapper officeMapper;

    @Autowired
    public OfficeService(OfficeMapper officeMapper) {
        this.officeMapper = officeMapper;
    }

    // store_code에 따른 사무실 리스트 전체 조회
    public List<OfficeDTO> findAllOffices(int storeCode) {
        // 전체조회긴 하지만 전달받은 파라미터에 해당하는 것만 전체조회
        return officeMapper.findAllOffices(storeCode);
    }

    public OfficeDTO findOfficeDetail(int officeCode) {
        OfficeDTO office = officeMapper.findOfficeDetail(officeCode);

        return office;
    }
}
