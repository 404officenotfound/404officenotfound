package com.office.notfound.samusil.model.service;

import com.office.notfound.common.util.FileUploadUtils;
import com.office.notfound.samusil.model.dao.OfficeMapper;
import com.office.notfound.samusil.model.dto.OfficeDTO;
import com.office.notfound.store.model.dao.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class OfficeService {

    private final OfficeMapper officeMapper;
    private final StoreMapper storeMapper;

    @Autowired
    public OfficeService(OfficeMapper officeMapper, StoreMapper storeMapper) {
        this.officeMapper = officeMapper;
        this.storeMapper = storeMapper;
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
    public void updateOffice(OfficeDTO office, MultipartFile newImage) {

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
