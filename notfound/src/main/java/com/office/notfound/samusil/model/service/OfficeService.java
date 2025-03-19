package com.office.notfound.samusil.model.service;

import com.office.notfound.common.util.FileUploadUtils;
import com.office.notfound.samusil.model.dao.OfficeMapper;
import com.office.notfound.samusil.model.dto.OfficeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class OfficeService {

//    @Value("${build/resources/main/static/img/office}")
//    private String IMAGE_DIR;
//
//    @Value("${img/office}")
//    private String IMAGE_URL;

//    @Value("${file.replace-dir}")
//    private String uploadDir;

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

//    @Transactional
//    public void insertOffice(OfficeDTO office, MultipartFile officeThumbnail) throws Exception {
//
//        // 이미지 저장
//        if (!officeThumbnail.isEmpty()) {
//
//            String imageName = UUID.randomUUID().toString().replace("-", "");
//            String thumbnailFileName = FileUploadUtils.saveOfficeFile(IMAGE_DIR, imageName, officeThumbnail);
//
//            office.setOfficeThumbnailUrl(thumbnailFileName);
//        }
//
//        // 상품 정보 저장
//        officeMapper.insertOffice(office);
//    }

    @Transactional
    public void updateOffice(OfficeDTO office) {
        officeMapper.updateOffice(office);
    }

    @Transactional
    public void deleteOffice(int officeCode) {

        officeMapper.deleteStore(officeCode);
    }

}
