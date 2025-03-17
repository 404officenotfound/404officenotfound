package com.office.notfound.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtils {

    public static String saveFile(String imageDir, String imageName, MultipartFile storeThumbnail)
            throws IOException {

        /* 프로젝트 루트 디렉토리의 절대 경로를 가져옴 */
        String projectPath = System.getProperty("user.dir");

        /* build 폴더 경로 생성 */
        String buildPath = projectPath + "/build/resources/main/static/img/store";
        File dir = new File(buildPath);

        /* 디렉토리가 존재하지 않으면 디렉토리 생성 */
        if (!dir.exists()) {
            dir.mkdirs();
        }

        /* 원본 파일 이름 가져오기 */
        String originFileName = storeThumbnail.getOriginalFilename();

        /* 확장자 추출 */
        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        /* 저장할 파일 이름 생성 */
        String savedFileName = imageName + ext;

        /* 저장할 파일 경로 생성 */
        File target = new File(dir, savedFileName);

        /* 파일 저장 */
        storeThumbnail.transferTo(target);

        /* 저장된 파일 이름 반환 */
        return savedFileName;
    }

    //    /* 이미지 삭제 기능은 SpringBoot 파일 업로드 기능을 복습하며 직접 구현해보세요~ */
//    public static void deleteFile(String filePath) {
//
//        File file = new File(filePath);
//        if (file.exists()) {
//            if(!file.delete()) {
//                throw new IOException("파일 삭제에 실패했습니다: " + filePath);
//            }
//        } else {
//            throw new IOException("삭제할 파일을 찾을 수 없습니다: " + filePath);
//        }
//
//        return ;
//    }
    public static boolean deleteFile(String fileDir, String fileName) {
        System.out.println("--- 이미지 삭제 시작 ---");
        System.out.println("fileDir = " + fileDir);
        System.out.println("fileName = " + fileName);

        /* build 디렉토리 경로를 기반으로 삭제할 파일 경로 생성 */
        String projectPath = System.getProperty("user.dir");
        String buildPath = projectPath + "/build/resources/main/static/image/store";
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

}