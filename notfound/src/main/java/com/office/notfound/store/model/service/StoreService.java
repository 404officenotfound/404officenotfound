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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.office.notfound.common.util.FileUploadUtils.saveStoreFile;

@Service
public class StoreService {

    @Value("${file.upload-dir}")
    private String uploadDir;

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

        String fileName = UUID.randomUUID().toString().replace("-", ""); // 파일명 생성
        Map<String, String> fileUrls = saveStoreFile(uploadDir, fileName, storeThumbnail, storeImg1, storeImg2, storeImg3);

        // DTO에 저장된 이미지 URL 세팅
        store.setStoreThumbnailUrl(fileUrls.get("storeThumbnailUrl"));
        store.setStoreImg1Url(fileUrls.get("storeImg1Url"));
        store.setStoreImg2Url(fileUrls.get("storeImg2Url"));
        store.setStoreImg3Url(fileUrls.get("storeImg3Url"));

        // 상품 정보 저장
        storeMapper.insertStore(store);
    }


//    @Transactional
//    public void createStore(StoreDTO store, MultipartFile storeImg1) throws Exception {
//
//        // 각 이미지의 저장된 URL을 저장할 변수 선언
//        String img1Url = null;
//
//        // 이미지 저장
//        if (!storeImg1.isEmpty()) {
//            String fileName = UUID.randomUUID().toString().replace("-", "");
//            img1Url = FileUploadUtils.saveStoreFile(uploadDir, fileName, storeImg1);
//        }
//        store.setStoreImg1Url(img1Url);
//
//        // 상품 정보 저장
//        storeMapper.insertStore(store);
//    }
//
//    @Transactional
//    public void createStore(StoreDTO store, MultipartFile storeImg2) throws Exception {
//
//        // 각 이미지의 저장된 URL을 저장할 변수 선언
//        String img2Url = null;
//
//        // 이미지 저장
//        if (!storeImg2.isEmpty()) {
//            String fileName = UUID.randomUUID().toString().replace("-", "");
//            img2Url = FileUploadUtils.saveStoreFile(uploadDir, fileName, storeImg2);
//        }
//        store.setStoreImg2Url(img2Url);
//
//
//        // 상품 정보 저장
//        storeMapper.insertStore(store);
//    }
//
//    @Transactional
//    public void createStore(StoreDTO store, MultipartFile storeImg3) throws Exception {
//
//        // 각 이미지의 저장된 URL을 저장할 변수 선언
//        String img3Url = null;
//
//        // 이미지 저장
//
//        if (!storeImg3.isEmpty()) {
//            String fileName = UUID.randomUUID().toString().replace("-", "");
//            img3Url = FileUploadUtils.saveStoreFile(uploadDir, fileName, storeImg3);
//        }
//
//        store.setStoreImg3Url(img3Url);
//
//
//        // 상품 정보 저장
//        storeMapper.insertStore(store);
//    }

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
    public void updateStore(StoreDTO store, MultipartFile newImage) {

        String oldImageName = storeMapper.findImageByStoreCode(store.getStoreCode());
        String oldImagePath = uploadDir + "/" + oldImageName;

        // 새로운 이미지가 업로드되었을 경우
        if (!newImage.isEmpty()) {
            // 기존 이미지 삭제
            File oldFile = new File(oldImagePath);
            if (oldFile.exists()) {
                oldFile.delete(); // 기존 파일 삭제
                System.out.println("[INFO] 기존 이미지 삭제 완료: " + oldImagePath);
            }

            // 새로운 이미지 저장
            String newFileName = UUID.randomUUID().toString().replace("-", "") +
                    newImage.getOriginalFilename().substring(newImage.getOriginalFilename().lastIndexOf("."));
            File newFile = new File(uploadDir + "/" + newFileName);
            try {
                newImage.transferTo(newFile);
                System.out.println("[INFO] 새 이미지 저장 완료: " + newFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("[ERROR] 파일 저장 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("[INFO] 새 이미지 저장 완료: " + newFile.getAbsolutePath());

            // DB에 새로운 이미지 정보 업데이트
            store.setStoreThumbnailUrl(newFileName);
            store.setStoreImg1Url(newFileName);
            store.setStoreImg2Url(newFileName);
            store.setStoreImg3Url(newFileName);
        }
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
