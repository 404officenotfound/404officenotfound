package com.office.notfound.samusil.model.service;

import com.office.notfound.samusil.model.dao.OfficeMapper;
import com.office.notfound.samusil.model.dto.OfficeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public OfficeDTO findOfficeByStore(int storeCode, int officeCode) {

        return officeMapper.findOfficeByStore(storeCode, officeCode);
    }

    @Transactional
    public void updateOffice(OfficeDTO office) {
        officeMapper.updateOffice(office);
    }

    @Transactional
    public void deleteOffice(int officeCode) {

        try {
            // officeCode에 해당하는 사무실이 해당 store에 속하는지 검증
            // 예를 들어: officeCode와 storeCode로 사무실을 삭제하는 경우
            int deleteCount = officeMapper.deleteOffice(officeCode);

            if (deleteCount == 0) {
                throw new IllegalArgumentException("해당 사무실을 삭제할 수 없습니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException("사무실 삭제 중 오류가 발생했습니다.", e);
        }

        officeMapper.deleteStore(officeCode);
    }
}
