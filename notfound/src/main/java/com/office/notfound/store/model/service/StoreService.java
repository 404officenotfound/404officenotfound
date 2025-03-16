package com.office.notfound.store.model.service;


import com.office.notfound.store.model.dao.StoreMapper;
import com.office.notfound.store.model.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StoreService {

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
}
