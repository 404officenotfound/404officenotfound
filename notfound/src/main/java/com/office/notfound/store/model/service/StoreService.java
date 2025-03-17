package com.office.notfound.store.model.service;


import com.office.notfound.common.util.FileUploadUtils;
import com.office.notfound.store.model.dao.StoreMapper;
import com.office.notfound.store.model.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class StoreService {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${image.image-url}")
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
    public void createStore(StoreDTO store, MultipartFile storeThumbnail) throws Exception {

        // 이미지 저장
        if (!storeThumbnail.isEmpty()) {

            String imageName = UUID.randomUUID().toString().replace("-", "");
            String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, storeThumbnail);

            store.setStoreThumbnail(replaceFileName);
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
}
