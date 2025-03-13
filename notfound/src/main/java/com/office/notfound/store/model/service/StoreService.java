package com.office.notfound.store.model.service;


import com.office.notfound.store.model.dao.StoreMapper;
import com.office.notfound.store.model.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StoreService {

    private final StoreMapper storeMapper;

    @Autowired
    public StoreService(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }
//    @Value("${imge.server-url}")
//    private String IMAGE_SERVER_URL;

//    public static List<String> findStoresByCity() {
//    }

//    public List<String> findStoresByCity() {
//        List<StoreDTO> cities = storeMapper.findStoresByCity();
//        return cities.stream()
//                .map(StoreDTO::getStoreCity) // DTO에서 storeCity 값만 추출
//                .distinct() // 중복 제거
//                .collect(Collectors.toList());
//    }

    public List<StoreDTO> findAllStores() {
//        List<StoreDTO> stores = storeMapper.findAllStores();
        return storeMapper.findAllStores();
    }

    public List<String> getGuByCity(String storeCity) {
        return storeMapper.findGuByCity(storeCity);     // StoreMapper에서 데이터
    }

//    public StoreDTO findStoreByCode(int storeCode) {
//        StoreDTO store = storeMapper.findStoreByCode(storeCode);
//
//        if (store != null) {
//            store.setStoreThumbnail(IMAGE_SERVER_URL + store.getStoreThumbnail());
//        }
//            return store;
//    }
}
