package com.office.notfound.samusil.model.dao;

import com.office.notfound.samusil.model.dto.OfficeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OfficeMapper {

    List<OfficeDTO> findAllOffices(int storeCode);

    OfficeDTO findOfficeDetail(int officeCode);

    OfficeDTO findOfficeByStore(int storeCode, int officeCode);

    void updateOffice(OfficeDTO office);

    void deleteStore(int officeCode);
}
