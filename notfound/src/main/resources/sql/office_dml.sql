

INSERT INTO tbl_member
(member_name, member_id, member_password, member_email, member_phone, member_enddate, member_endstatus)
VALUES
    ('정상협', 'user01', '$2a$10$cYuXuzmhojpxD2AV7CoZX.dq1qVXnWUlmdvx1Y3P5xezD5z968nNu', 'user01@gmail.com', '010-1234-5678', NULL, 'N'),
    ('일반사용자','user02', '$2a$10$A.i2AkaE/4qhjwo45MHofeShPXOLsD0HanK7Ce..npQj7GKslsJR.', 'user02@gmail.com', '010-1234-5678', NULL, 'N' ),
    ('일반사용자2','user03', '$2a$10$bxvD9ctVmDoZjQAXv4nfVOQx6zyOR9hc2ELFKxK21wjRaHf/O3CQm', 'user03@gmail.com', '010-1234-5678', NULL, 'N' ),
    ('일반사용자3','user04', '$2a$10$fWGThR11/nx7oDngNCTpiOq14g6tpjwf61TskCEZ1yFIIdI1CHTQu', 'user04@gmail.com', '010-1234-5678', NULL, 'N' );



INSERT INTO tbl_authority
(authority_name)
VALUES
    ('ADMIN'), ('USER');


INSERT INTO tbl_member_role
(member_code, authority_code)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 2),
    (4, 2);


INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('마곡나루역 점', '서울', '강서구', '마곡중앙로 136 LG 아트센터', 37.565223, 126.829091,
        '서울식물원 입구에 위치해 녹지 뷰.',
        'https://dynamicmedia.dow.com/is/image/dow/AdobeStock_238505200?qlt=82&wid=1920&ts=1714398605717&dpr=off',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('건대입구역 점', '서울', '광진구', '능동로 87', 37.539044, 127.069665,
        '지하철 역 출구 바로 앞 위치해 접근성 좋음.',
        'https://cdn.bizwatch.co.kr/news/photo/2024/07/05/eefac657c3af6e22154c451a39e090af.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('홍대입구역 점', '서울', '마포구', '양화로 188', 37.557855, 126.926266,
        '지하철 역사와 연결되어 있어서 날씨와 상관 없이 접근 가능.',
        'https://biz.chosun.com/resizer/v2/BVYQ6E247BFJJJBLY7UM6ZAO7I.jpg?auth=6f6153a65fe26ae528c6bd663ba361d7ca587edceeb384ed25a5a48d6021bec9&width=616',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('공덕역 점', '서울', '마포구', '백범로 192', 37.543345, 126.951411,
        '서울역 KTX와 공항철도 한 정거장 위치로 출장시 사용하기 좋은 곳에 위치.',
        'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20140424_235%2Fhans3513_1398266502361QJ2ni_JPEG%2Fs-oil.jpg&type=sc960_832',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('서울숲 점', '서울', '성동구', '성수동 1가', 37.545016, 127.043806,
        '녹지 앞에 위치해 경치 좋은 곳에 위치.',
        'https://cphoto.asiae.co.kr/listimglink/1/2021012808380948905_1611790690.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide03.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide01.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('디지털미디어시티역 점', '서울', '마포구', '성암로 179', 37.576153, 126.898829,'지하철 역 출구 바로 앞 큰길에 위치에 접근성 최상. 라운지 한 켠에 집중을 위한 독방 구비.',
        'https://postfiles.pstatic.net/MjAxOTExMjJfMTc2/MDAxNTc0NDAwNTg1OTk0.c5TjZYl20F3JjCYjCDE8szKCCyKUpmWquJHnZA1lHOgg.8WxoMOHzX1J4RSRH81mxEIn6i-NZTgtalF-tOGI7USAg.JPEG.knscoco/1574400584801.jpg?type=w966',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-focusroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide01.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('광화문광장 점', '서울', '종로구', '종로 1 교보생명 빌딩', 37.570952, 126.977737,
        '지하철 역과 직접 이어져 있어 접근성이 좋고, 출퇴근에 편리.',
        'https://blog.igisam.com/app/uploads/2023/10/2010%EB%85%84-%EB%A6%AC%EB%AA%A8%EB%8D%B8%EB%A7%81%EC%9D%84-%EB%A7%88%EB%AC%B4%EB%A6%AC-%ED%95%9C-%EA%B4%91%ED%99%94%EB%AC%B8-%EA%B5%90%EB%B3%B4%EB%B9%8C%EB%94%A9%EC%9D%98-%EB%AA%A8%EC%8A%B5-1024x727.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('종각역 점', '서울', '종로구', '종로 47 에스씨제일은행본점빌딩', 37.571044, 126.982360,
        '입점사 한정 급여 거래은행을 제일은행으로 계약 시, 제일은행 수수료 면제.',
        'https://img6.yna.co.kr/photo/cms/2023/05/17/43/PCM20230517000243002_P2.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('청라국제도시 점', '인천', '서구', '에코로 181', 37.555914, 126.628034,
        '공항철도 청라국제도시 역 바로 앞 위치. 최고의 접근성. 입점사 한정 급여 거래은행을 하나은행으로 계약 시, 하나은행 수수료 면제.',
        'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzA1MjBfODQg%2FMDAxNDk1MjQ0OTAzNjQ5.23v57ZMFgY9Ltosw_G2VYlpd0usIYTOjjo3PJjqym2Ag.gnDtiH25fyxppFdHf6dDpt8xSkR-bWzrTT-o9w2MSUcg.JPEG.aviator2865%2FDSC03742.JPG&type=sc960_832',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('송도국제도시 점', '인천', '연수구', '인천타워대로 241', 37.392217, 126.634388, '입주사 대상 1~3층에 입점한 한의원 침 치료 한정 10%할인',
        'https://www.focusincheon.com/news/photo/202212/2108_2864_154.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('제주공항 점','제주', '제주시', '서해안로 624', 33.516796, 126.503541, '제주도 해안가에 위치해 경치가 좋음.',
        'https://image.goodchoice.kr/affiliate/2023/09/20/650a8707c9e5e.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('중앙로 점', '대구', '중구', '국채보상로 586 교보생명 빌딩', 35.870494, 128.594926, '중앙로 역과 도보 3분거리. 출퇴근에 편리.',
        'https://www.yeongnam.com/mnt/file/200503/20050324.010101339190002i1.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide06.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('라페스타 점', '경기', '고양시 일산동구', '무궁화로 32-34 라페스타', 37.661203, 126.768914, '입주사 한정 고객 라페스타 건물 내 롯데시네마 주1회 50%할인 중.',
        '라운지 공용 커피머신 외 입주사 사무실 마다 네스프레소 캡슐 커피 머신 제공.',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide06.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES ('판교역 점', '경기', '성남시 분당구', '판교역로 166', 37.395194, 127.110458, '판교역 바로 앞에 위치해 접근성이 좋고, IT 사업체가 모인 곳에 위치해 타회사와의 대면 미팅 시 편리.',
        'https://dimg.donga.com/wps/NEWS/IMAGE/2022/10/24/116112232.1.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-focusroom.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail_url, store_img1_url, store_img2_url, store_img3_url)
VALUES
    ('수원화성 점', '경기', '수원시 장안구', '정조로 825', 37.282575, 127.015819,
     '건물 2층에 입점한 안과에서 입주사 직원 한정 무료 시력검진 진행 중.',
     'https://cdn.hitnews.co.kr/news/photo/202403/52804_70142_406.png',
     'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
     'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp',
     'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide06.jpg');


INSERT INTO tbl_office (store_code, office_type, office_num, office_price, office_thumbnail_url)
VALUES
    (1, '1인실', '101', 500000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (1, '4인실', '102', 1500000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (2, '1인실', '201', 550000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (2, '10인실', '202', 4000000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (3, '1인실', '301', 480000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (3, '4인실', '302', 1600000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (4, '10인실', '401', 4200000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (4, '1인실', '402', 520000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (5, '4인실', '501', 1400000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (5, '1인실', '502', 500000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (6, '10인실', '601', 4500000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (6, '4인실', '602', 1550000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (7, '1인실', '701', 470000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (7, '4인실', '702', 1450000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (8, '1인실', '801', 490000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (8, '10인실', '802', 4300000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (9, '4인실', '901', 1500000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (9, '1인실', '902', 500000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (10, '10인실', '1001', 4000000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (10, '4인실', '1002', 1600000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (11, '1인실', '1101', 470000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (11, '10인실', '1102', 4100000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (12, '4인실', '1201', 1550000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (12, '1인실', '1202', 490000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (13, '1인실', '1301', 520000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (13, '10인실', '1302', 4300000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (14, '4인실', '1401', 1500000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (14, '1인실', '1402', 500000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (15, '1인실', '1501', 480000, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
    (15, '4인실', '1502', 1600000, 'http://www.dodreamoffice.com/img/why_img1.jpg');

INSERT INTO tbl_reservation (member_code, office_code, start_datetime, end_datetime, total_price, reservation_status)
VALUES
    (2, 1, '2025-03-15 08:00:00', '2025-03-15 10:00:00', 100, '예약완료'),
    (2, 4, '2025-03-18 10:00:00', '2025-03-18 12:00:00', 1, '예약완료'),
    (2, 6, '2025-03-20 12:00:00', '2025-03-20 14:00:00', 160, '예약완료'),
    (2, 9, '2025-03-20 14:00:00', '2025-03-20 16:00:00', 20, '예약완료'),
    (2, 7, '2025-03-22 18:00:00', '2025-03-22 20:00:00', 4500000, '예약완료'),
    (2, 3, '2025-03-25 16:00:00', '2025-03-25 18:00:00', 500000, '예약취소'),
    (2, 10, '2025-03-27 12:00:00', '2025-03-27 14:00:00', 4000000, '예약완료'),
    (2, 12, '2025-03-27 14:00:00', '2025-03-27 16:00:00', 1550000, '예약완료'),
    (2, 15, '2025-03-27 16:00:00', '2025-03-27 18:00:00', 500000, '예약완료'),
    (4, 5, '2025-04-01 08:00:00', '2025-04-01 10:00:00', 1500000, '예약완료'),
    (3, 8, '2025-04-05 10:00:00', '2025-04-05 12:00:00', 500000, '예약취소'),
    (2, 11, '2025-04-05 12:00:00', '2025-04-05 14:00:00', 1500000, '예약취소');

-- 🔥 결제 데이터 삽입
INSERT INTO tbl_payment (member_code, payment_date, payment_method, payment_amount, payment_status, imp_uid, merchant_uid)
VALUES
    (1, '2025-03-15 07:50:00', '카드', 1, '결제완료', 'IMP123456', 'ORD123456'),
    (2, '2025-03-18 09:50:00', '계좌이체', 100, '결제완료', 'IMP123457', 'ORD123457'),
    (2, '2025-03-20 11:50:00', '카드', 200, '결제완료', 'IMP123458', 'ORD123458'),
    (2, '2025-03-22 17:50:00', '카드', 2, '결제완료', 'IMP123459', 'ORD123459'),
    (2, '2025-03-25 15:50:00', '계좌이체', 500000, '결제취소', 'IMP123460', 'ORD123460'),
    (2, '2025-03-27 11:50:00', '카드', 6050000, '결제완료', 'IMP123461', 'ORD123461'),
    (4, '2025-04-01 07:50:00', '계좌이체', 1500000, '결제완료', 'IMP123462', 'ORD123462'),
    (3, '2025-04-05 09:50:00', '카드', 2000000, '결제취소', 'IMP123463', 'ORD123463');

-- 🔥 예약-결제 관계 데이터 삽입
INSERT INTO tbl_reservation_payment (reservation_code, payment_code)
VALUES
    (1, 1), (2, 2), (3, 3), (4, 3), (5, 4),
    (6, 5), (7, 6), (8, 6), (9, 6),
    (10, 7), (11, 8), (12, 8);



-- 이벤트 테이블 (tbl_event) 더미데이터
INSERT INTO tbl_event (event_title, event_content, event_img, event_status) VALUES
                                                                                ('404 OFFICE NOT FOUND 1호점 마곡나루역 오픈 이벤트!!', '따뜻한 봄날, 404 OFFICE NOT FOUND가 1호점 마곡나루역을 시작으로 새롭게 오픈합니다!! \n자세한 이벤트 내용은 전화로 문의하세요.', '새로고.jpg', '진행마감'),
                                                                                ('404 OFFICE NOT FOUND 2호점 건대입구역 오픈기념 세라젬 구비', '집중해서 업무할만한 장소를 찾는다면 쾌적하고 저렴한 공유오피스 건대입구역 404 OFFICE NOT FOUND가 있습니다. \n오랜 시간 업무하시는 입주사분들을 위해 세라젬을 구비했습니다.\n휴식이 필요할 때 이용해보세요!', '새로고.jpg', '진행중'),
                                                                                ('종각역점 무료 주차 서비스 진행', '종각역점에서 무료 주차 서비스를 제공합니다. \n지금 바로 문의하세요!!', '새로고.jpg', '진행중'),
                                                                                ('10호점 인천점, 11호점 대구점 3월 오픈 예정 사전 접수 이벤트', '공유오피스 404 OFFICE NOT FOUND가 가맹사업을 시작한지 2달 만에 10호점 인천점, 11호점 대구점 까지 오픈을 하게 되었습니다.\n공유오피스를 이용하시는 모든 분들께 아메리카노를 무료로 제공합니다.', '새로고.jpg', '진행중'),
                                                                                ('2025 신년 행운 복권 대잔치 ', '새해 첫 운을 시험해보세요! \n공유 오피스를 이용하시는 모든 분께 복권을 지급해드립니다. \n당첨자는 1개월 무료 이용권, 스타벅스 기프트 카드 등 푸짐한 경품을 받을 수 있습니다!', '새로고.jpg', '진행마감');


-- 자주 묻는 질문 (tbl_faq) 더미데이터
INSERT INTO tbl_faq (faq_title, faq_content) VALUES
                                                 ('공유 오피스란 무엇인가요?', '공유 오피스는 여러 회사가 하나의 공간을 공유하여 사용하는 사무 공간입니다.\n개인이나 소규모 기업, 프리랜서들이 비용 효율적으로 사무공간을 이용할 수 있도록 설계된 장소입니다.'),
                                                 ('오늘 당장 계약 할 수 있나요? 비대면으로도 되나요?', '입금 및 계약정보 제공해주시면 10분 이내로 계약 체결 가능합니다.(점심시간 및 휴무일 제외)\n
 안전한 비대면 전자계약 플랫폼을 통해 계약이 이루어집니다. '),
                                                 ('현장실사가 나오면 지원이 되나요?', '최소 1일 전에 알려주시면 무료로 실사지원이 됩니다. \n실사일에 대표님이 오셔서 직접 실사관 안내를 해주셔야 합니다. '),
                                                 ('우편물이 오면 알려주시나요?', '담당 매니저가 실시간으로 문자 연락드립니다. \n필요시 우편물을 개봉해 사진촬영 또는 스캔해 보내드리기도 합니다. '),
                                                 ('주차 공간은 있나요?', '일부 공유 오피스에는 주차 공간이 제공됩니다. \n주차 공간이 있는 경우, 예약 시 함께 확인하실 수 있습니다.'),
                                                 ('택배서비스는 어떻게 이용하고 요금은요?', '대표님 자택 또는 사업장에서 패킹 및 송장스티커를 붙여서 건물 앞에 드롭하시면 되고 요금은 익월 정산합니다.\n
픽업은 평일 주 5일 오후에 택배사에서 진행합니다. 택배요금은 소형(세변의합 80CM 이내) 기준 2,120원(VAT포함)입니다.'),
                                                 ('제가 양초를 만드는 공방을 하는데요. 작업을 해도 되나요?', '404 OFFICE NOT FOUND는 공유공방이 아닌 사무공간입니다. \n죄송하지만 냄새, 소음등의 이유로 공방의 상주사무실 입점은 어렵습니다.'),
                                                 ('이용 요금은 어떻게 되나요?', '이용 요금은 선택한 사무실 유형과 이용 시간에 따라 달라집니다.'),
                                                 ('강의를 많이 하는 편이에요. 소음이 있을 것 같은데 무관할까요?', '강의시간 및 횟수가 많다면 죄송하지만 이용이 어렵습니다. 방음이 잘 된다고해도 지속적인 약간의 소음이 옆방에 전달 될 수 있습니다.'),
                                                 ('다른 사람들과 함께 사용하는 공간인가요?', '네, 공유 오피스는 여러 사용자가 함께 사용하는 공간입니다. 개인 공간과 공용 공간이 구분되어 있어, 업무에 집중할 수 있습니다.');


-- 1:1 문의 게시판
INSERT INTO tbl_inquiry (member_code, member_id, inquiry_title, inquiry_content, inquiry_created_at, inquiry_answer_state, inquiry_admin_answer) VALUES
                                                                                                                                                     (3, 'user01', '예약 취소는 어떻게 하나요?', '예약을 취소하려면 어떤 절차를 따라야 하는지 알고 싶습니다. 예약이 이미 확정된 상태에서 취소가 가능한지 확인하고 싶어요.', '2025-03-12 10:30:00', '답변 완료', '안녕하세요 고객님, 문의해 주셔서 감사합니다.\n예약 취소는 로그인 → 마이페이지 → 예약관리 페이지에서 가능합니다. \n 기타 궁금한 내용이 있으시면 언제든지 문의해 주세요.\n감사합니다.'),
                                                                                                                                                     (1, 'user02', '주차 공간 예약이 가능한가요?', '지점에 주차 공간이 부족한 것 같은데, 사무실 예약과 함께 주차 공간도 예약할 수 있는지 확인하고 싶습니다.', '2025-03-12 11:00:00', '답변 대기', NULL),
                                                                                                                                                     (2, 'user03', '인터넷 속도가 너무 느려요', '공유 오피스에서 제공하는 인터넷 속도가 매우 느린데, 해결 방법이나 점검을 요청할 수 있을까요?', '2025-03-12 12:15:00', '답변 대기', NULL),
                                                                                                                                                     (4, 'user04', '회의실 예약은 어떻게 하나요?', '회의실을 예약하려면 어떤 절차를 거쳐야 하는지 궁금합니다. 온라인으로 예약 가능한가요?', '2025-03-12 12:45:00', '답변 완료', '안녕하세요 고객님, 문의해 주셔서 감사합니다.\n회의실 예약은 지점 → 지역별 조회 → 각 지점별로 조회하여 원하는 사무실을 선택해 예약하실 수 있습니다. \n 기타 궁금한 내용이 있으시면 언제든지 문의해 주세요.\n감사합니다.'),
                                                                                                                                                     (1, 'user03', '사무실 호실에 대한 정보가 부족해요', '각 사무실 호실에 대한 상세 정보가 부족한 것 같습니다. 사진이나 추가 정보를 제공해주실 수 있나요?', '2025-03-12 13:00:00', '답변 대기', NULL),
                                                                                                                                                     (2, 'user02', '사무실에서 개인 물품을 두고 가도 되나요?', '공유 오피스를 사용한 후 개인 물품을 사무실에 두고 가도 괜찮은지 궁금합니다. 물품 보관 정책에 대해 알고 싶어요.', '2025-03-12 14:00:00', '답변 대기', NULL),
                                                                                                                                                     (3, 'user01', '장기 예약시 별도의 할인이 있나요?', '사무실이 너무 좋아서 장기 예약을 하고 싶습니다. 별도의 할인이 있나요?', '2025-03-12 14:30:00', '답변 완료', '안녕하세요 고객님, 문의해 주셔서 감사합니다.\n장기예약시 할인은 제공하고 있지 않고 있습니다. 자세한 사항은 별도 문의 바랍니다.\n감사합니다. '),
                                                                                                                                                     (4, 'user04', '공유 오피스 이용 중 규정 위반 시 처벌이 있나요?', '공유 오피스를 이용하면서 규정을 위반하면 어떤 처벌이 있을 수 있는지 궁금합니다. 이에 대한 정보가 필요해요.', '2025-03-12 15:00:00', '답변 완료', '안녕하세요 고객님, 문의해 주셔서 감사합니다.\n규정 위반 시, 1회 경고 후 반복 시 이용 제한이 있을 수 있습니다. \n 기타 궁금한 내용이 있으시면 언제든지 문의해 주세요.\n감사합니다.');



-- 리뷰 게시판
-- tbl_member와 tbl_payment 테이블에서 각각 회원 정보와 결제 정보가 존재한다고 가정했을 때 작성된 더미 데이터
INSERT INTO tbl_review (member_code, payment_code, member_id, review_title, review_content, review_created_at, review_rating, review_image) VALUES
                                                                                                                                                (1, 1, 'user01', '편리한 사무실 공간', '오픈한지 얼마 되지 않은 곳이라 사람 없이 편안한 분위기에서 업무에 집중할 수 있었습니다. 사무실이 깨끗해서 만족스러웠습니다. ', '2025-03-12 16:00:00', 5, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (2, 8, 'user02', '위치가 좋지만 소음이 조금 아쉬워요', '위치는 정말 좋은데, 주변 소음이 조금 신경 쓰였습니다. 그 외에는 만족스러웠어요!!', '2025-03-12 16:30:00', 4, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (3, 6, 'user03', '회의실 예약이 편리합니다!', '회의실 예약 시스템이 아주 편리하고 직관적이어서 정말 좋았습니다. 다만, 더 많은 회의실이 있었으면 좋겠습니다!', '2025-03-12 17:00:00', 4, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (4, 3, 'user04', '라페스타점 인터넷 속도 빠르고 좋아요!!', '인터넷 속도가 매우 빠르고 안정적이어서 업무에 큰 도움이 되었습니다! \n피시방이 따로 없네요.', '2025-03-12 17:30:00', 5, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (3, 2, 'user01', '공유 오피스에서의 첫 경험', '첫 번째로 이용한 공유 오피스였는데, 직원들이 친절하고 공간이 아늑해서 편하게 일할 수 있었습니다.', '2025-03-12 18:00:00', 5, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (1, 2, 'user02', '가성비 좋아요', '가성비는 좋은데 조금 더 편안한 의자와 큰 테이블이 필요할 것 같습니다. 그 외에는 만족했습니다.', '2025-03-12 18:30:00', 4, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (3, 5, 'user03', '회의실이 부족해요', '회의실 수가 부족해서 예약이 어려웠습니다. 더 많은 회의실을 추가해주시면 좋겠습니다.', '2025-03-12 19:00:00', 3, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (4, 7, 'user04', '새로 생긴 곳이라 그런지 시설이 좋네요.', '위치도 정말 좋고 시설도 매우 깨끗해 만족스러웠습니다. \n하지만 주차공간이 조금 부족한 느낌이었습니다. \n주차 공간이 필요해요', '2025-03-12 19:30:00', 4, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (2, 5, 'user01', '송도국제도시점 잘 이용 했습니다.', '공간이 넓고 조용해서 업무에 집중하기 좋았습니다.\n하지만 간식 제공이 부족한 점이 아쉬웠어요. 더 많은 간식을 제공해 주셨으면 좋겠습니다.', '2025-03-12 20:00:00', 4, 'http://www.dodreamoffice.com/img/why_img1.jpg'),
                                                                                                                                                (1, 3, 'user04', '최고의 공유 오피스', '여기서 일하는 경험이 정말 좋았습니다. 모든 것이 잘 갖춰져 있어서 다음에도 또 이용할 생각입니다.', '2025-03-12 20:30:00', 5, 'http://www.dodreamoffice.com/img/why_img1.jpg');


COMMIT;