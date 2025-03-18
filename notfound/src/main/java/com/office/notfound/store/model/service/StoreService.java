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

        return store;
    }

    @Transactional
    public void createStore(StoreDTO store, MultipartFile storeThumbnail, MultipartFile storeImg1, MultipartFile storeImg2, MultipartFile storeImg3) throws Exception {

        // 이미지 저장
        if (!storeThumbnail.isEmpty()) {

            String imageName = UUID.randomUUID().toString().replace("-", "");
            String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, storeThumbnail, storeImg1, storeImg2, storeImg3);

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

}
