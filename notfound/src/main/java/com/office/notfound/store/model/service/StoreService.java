package com.office.notfound.store.model.service;


import com.office.notfound.common.util.FileUploadUtils;
import com.office.notfound.store.model.dao.StoreMapper;
import com.office.notfound.store.model.dto.StoreDTO;
import org.apache.catalina.Store;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class StoreService {

    @Value("build/resources/main/static/img/store")
    private String IMAGE_DIR;

    @Value("/img/store/")
    private String IMAGE_URL;

    private final StoreMapper storeMapper;

    @Autowired
    public StoreService(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public List<StoreDTO> findAllStores() {
        List<StoreDTO> stores = storeMapper.findAllStores();
        return storeMapper.findAllStores();
    }

    public StoreDTO findStoreByCode(int storeCode) {
        StoreDTO store = storeMapper.findStoreByCode(storeCode);

        return storeMapper.findStoreByCode(storeCode);
    }

    @Transactional
    public void createStore(StoreDTO store, MultipartFile storeThumbnail, MultipartFile storeImg1, MultipartFile storeImg2, MultipartFile storeImg3) throws Exception {

        // 이미지 저장
        if (!storeThumbnail.isEmpty()) {

            String imageName = UUID.randomUUID().toString().replace("-", "");
            String replaceFileName = FileUploadUtils.saveStoreFile(IMAGE_DIR, imageName, storeThumbnail, storeImg1, storeImg2, storeImg3);

            store.setStoreThumbnailUrl(replaceFileName);
            store.setStoreImg1Url(replaceFileName);
            store.setStoreImg2Url(replaceFileName);
            store.setStoreImg3Url(replaceFileName);
        }

        // 상품 정보 저장
        storeMapper.insertStore(store);
    }

    public List<String> getDistinctCities() {

        return storeMapper.findDistinctCities();
    }


    public List<String> getGuByCity(String city) {

        return storeMapper.findGuByCity(city);
    }

    public List<StoreDTO> findStoresByCityAndGu(String city, String gu) {

        return storeMapper.findStoresByCityAndGu(city, gu);
    }

    @Transactional
    public void updateStore(StoreDTO store) {
        // 오류 발생시 자동 롤백
        storeMapper.updateStore(store);
    }

    @Transactional
    public void deleteStore(int storeCode) {
        // 지점 이미지 정보 조회
        StoreDTO store = storeMapper.findStoreByCode(storeCode);

        if (store == null) {
            throw new IllegalArgumentException("선택 지점이 존재하지 않습니다.");
        }

        // 이미지 파일 삭제
        if (store.getStoreThumbnailUrl() != null && !store.getStoreThumbnailUrl().isEmpty()) {
            String filePathStr = store.getStoreThumbnailUrl();

            if (store.getStoreThumbnailUrl().startsWith("http")) {
                throw new IllegalArgumentException("파일 삭제는 서버 내부 파일에 대해서만 가능합니다.");
            }
            try {
                // 스토어의 스토어썸네일의 경로 가져오기
                Path filePath = Paths.get(store.getStoreThumbnailUrl());
                Files.deleteIfExists(filePath);
            } catch (Exception e) {
                throw new RuntimeException("이미지 삭제 중 오류가 발생했습니다.", e);
            }
        }
        // 상품 정보 삭제
        storeMapper.deleteStore(storeCode);
    }
}
