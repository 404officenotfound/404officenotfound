package com.office.notfound.store.model.dao;

import com.office.notfound.store.model.dto.StoreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<StoreDTO> findAllStores();

    StoreDTO findStoreByCode(int storeCode);

    void insertStore(StoreDTO store);

    List<String> findDistinctCities();

    List<String> findGuByCity(String city);
}

