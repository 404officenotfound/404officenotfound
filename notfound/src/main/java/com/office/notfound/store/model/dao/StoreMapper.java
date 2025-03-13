package com.office.notfound.store.model.dao;

import com.office.notfound.store.model.dto.StoreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<StoreDTO> findAllStores();

    List<StoreDTO> findStoresByCity(String storeCity);

    List<String> findGuByCity(String storeCity);

    void insertStore(StoreDTO store);

    void updateStore(StoreDTO store);

    void deleteStore(StoreDTO store);

    StoreDTO findStoreByCode(int storeCode);
}
