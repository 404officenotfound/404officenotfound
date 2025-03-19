package com.office.notfound.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtils {


    public static String saveFile(String imageDir, String imageName, MultipartFile storeThumbnail, MultipartFile storeImg1, MultipartFile storeImg2, MultipartFile storeImg3)
            throws IOException {

        /* 프로젝트 루트 디렉토리의 절대 경로를 가져옴 */
        String projectPath = System.getProperty("user.dir")  + "/notfound";

        /* build 폴더 경로 생성 */
        String buildPath = projectPath + "/build/resources/main/static/img/store";
        File dir = new File(buildPath);

        /* 디렉토리가 존재하지 않으면 디렉토리 생성 */
        if (!dir.exists()) {
            dir.mkdirs();
        }

        /* 원본 파일 이름 가져오기 */
        String originFileName = storeThumbnail.getOriginalFilename();
        String fileName1 = storeImg1.getOriginalFilename();
        String fileName2 = storeImg2.getOriginalFilename();
        String fileName3 = storeImg3.getOriginalFilename();

        /* 확장자 추출 */
        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        /* 저장할 파일 이름 생성 */
        String savedFileName = imageName + ext;
        System.out.println("Store savedFileName------------->" + savedFileName);

        /* 저장할 파일 경로 생성 */
        File target = new File(dir, savedFileName);
        System.out.println("Store target---------> = " + target);

        /* 파일 저장 */
        storeThumbnail.transferTo(target);
        storeImg1.transferTo(target);
        storeImg2.transferTo(target);
        storeImg3.transferTo(target);

        /* 저장된 파일 이름 반환 */
        return "/img/store/" + savedFileName;
    }

    public static boolean deleteFile(String fileDir, String fileName) {
        System.out.println("--- 이미지 삭제 시작 ---");
        System.out.println("fileDir = " + fileDir);
        System.out.println("fileName = " + fileName);

        /* build 디렉토리 경로를 기반으로 삭제할 파일 경로 생성 */
        String projectPath = System.getProperty("user.dir") + "/notfound";
        String buildPath = projectPath + "/build/resources/main/static/img/store";
        Path filePath = Paths.get(buildPath, fileName);

        try {
            /* 파일 존재 여부 확인 후 삭제 */
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("이미지 삭제 성공: " + filePath.toString());
                return true;
            } else {
                System.out.println("삭제할 파일이 존재하지 않습니다: " + filePath.toString());
            }
        } catch (IOException e) {
            System.out.println("이미지 삭제 중 오류 발생: " + e.getMessage());
        }

        return false;
    }


    public static String saveReviewFile(String imageDir, String imageName, MultipartFile reviewThumbnail)
            throws IOException {

        /* 프로젝트 루트 디렉토리의 절대 경로를 가져옴 */
        String projectPath = System.getProperty("user.dir") + "/notfound";

        /* build 폴더 경로 생성 */
        String buildPath = projectPath + "/build/resources/main/static/img/review";
        File dir = new File(buildPath);

        /* 디렉토리가 존재하지 않으면 디렉토리 생성 */
        if (!dir.exists()) {
            dir.mkdirs();
        }

        /* 원본 파일 이름 가져오기 */
        String originFileName = reviewThumbnail.getOriginalFilename();

        /* 확장자 추출 */
        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        /* 저장할 파일 이름 생성 */
        String savedFileName = imageName + ext;
        System.out.println("savedFileName------------->" + savedFileName);

        /* 저장할 파일 경로 생성 */
        File target = new File(dir, savedFileName);
        System.out.println("target---------> = " + target);

        /* 파일 저장 */
        reviewThumbnail.transferTo(target);

        /* 저장된 파일 이름 반환 */
        return "/img/review/" + savedFileName;
    }

/* build 디렉토리 경로를 기반으로 삭제할 파일 경로 생성 *//*

        String projectPath = System.getProperty("user.dir") + "/notfound";;
        String buildPath = projectPath + "/build/resources/main/static/img/review";
        Path filePath = Paths.get(buildPath, fileName);

        try {
            */
/* 파일 존재 여부 확인 후 삭제 *//*

            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("이미지 삭제 성공: " + filePath.toString());
                return true;
            } else {
                System.out.println("삭제할 파일이 존재하지 않습니다: " + filePath.toString());
            }
        } catch (IOException e) {
            System.out.println("이미지 삭제 중 오류 발생: " + e.getMessage());
        }

        return false;
    }
*/
    public static boolean deleteReviewFile() {

        System.out.println("--- 이미지 삭제는 직접 구현해보세요~ ---");
        return false;
    }

}