INSERT INTO tbl_member
(member_name, member_id, member_password, member_email, member_phone, member_enddate, member_endstatus)
VALUES
    ('홍길동', 'hong123', 'password123', 'hong@example.com', '010-1234-5678', NULL, 'N'),
    ('김철수', 'kim123', 'password456', 'kim@example.com', '010-9876-5432', NULL, 'N'),
    ('이영희', 'lee123', 'password789', 'lee@example.com', '010-1122-3344', NULL, 'N'),
    ('박지은', 'park123', 'password000', 'park@example.com', '010-2233-4455', NOW(), 'Y');

INSERT INTO tbl_authority
(authority_code , authority_name)
VALUES
    (1,'ADMIN'),
    (2,'USER');

INSERT INTO tbl_member_role
(member_code, authority_code)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 2);

INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('마곡나루역 점', '서울', '강서구', '마곡중앙로 136 LG 아트센터', 37.565223, 126.829091,
        '서울식물원 입구에 위치해 녹지 뷰.',
        'https://dynamicmedia.dow.com/is/image/dow/AdobeStock_238505200?qlt=82&wid=1920&ts=1714398605717&dpr=off',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('건대입구역 점', '서울', '광진구', '능동로 87', 37.539044, 127.069665,
        '지하철 역 출구 바로 앞 위치해 접근성 좋음.',
        'https://cdn.bizwatch.co.kr/news/photo/2024/07/05/eefac657c3af6e22154c451a39e090af.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('홍대입구역 점', '서울', '마포구', '양화로 188', 37.557855, 126.926266,
        '지하철 역사와 연결되어 있어서 날씨와 상관 없이 접근 가능.',
        'https://biz.chosun.com/resizer/v2/BVYQ6E247BFJJJBLY7UM6ZAO7I.jpg?auth=6f6153a65fe26ae528c6bd663ba361d7ca587edceeb384ed25a5a48d6021bec9&width=616',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('공덕역 점', '서울', '마포구', '백범로 192', 37.543345, 126.951411,
        '서울역 KTX와 공항철도 한 정거장 위치로 출장시 사용하기 좋은 곳에 위치.',
        'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20140424_235%2Fhans3513_1398266502361QJ2ni_JPEG%2Fs-oil.jpg&type=sc960_832',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('서울숲 점', '서울', '성동구', '성수동 1가', 37.545016, 127.043806,
        '녹지 앞에 위치해 경치 좋은 곳에 위치.',
        'https://cphoto.asiae.co.kr/listimglink/1/2021012808380948905_1611790690.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide03.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide01.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('디지털미디어시티역 점', '서울', '마포구', '성암로 179', 37.576153, 126.898829,'지하철 역 출구 바로 앞 큰길에 위치에 접근성 최상. 라운지 한 켠에 집중을 위한 독방 구비.',
        'https://postfiles.pstatic.net/MjAxOTExMjJfMTc2/MDAxNTc0NDAwNTg1OTk0.c5TjZYl20F3JjCYjCDE8szKCCyKUpmWquJHnZA1lHOgg.8WxoMOHzX1J4RSRH81mxEIn6i-NZTgtalF-tOGI7USAg.JPEG.knscoco/1574400584801.jpg?type=w966',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-focusroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide01.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('광화문광장 점', '서울', '종로구', '종로 1 교보생명 빌딩', 37.570952, 126.977737,
        '지하철 역과 직접 이어져 있어 접근성이 좋고, 출퇴근에 편리.',
        'https://blog.igisam.com/app/uploads/2023/10/2010%EB%85%84-%EB%A6%AC%EB%AA%A8%EB%8D%B8%EB%A7%81%EC%9D%84-%EB%A7%88%EB%AC%B4%EB%A6%AC-%ED%95%9C-%EA%B4%91%ED%99%94%EB%AC%B8-%EA%B5%90%EB%B3%B4%EB%B9%8C%EB%94%A9%EC%9D%98-%EB%AA%A8%EC%8A%B5-1024x727.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('종각역 점', '서울', '종로구', '종로 47 에스씨제일은행본점빌딩', 37.571044, 126.982360,
        '입점사 한정 급여 거래은행을 제일은행으로 계약 시, 제일은행 수수료 면제.',
        'https://img6.yna.co.kr/photo/cms/2023/05/17/43/PCM20230517000243002_P2.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('청라국제도시 점', '인천', '서구', '에코로 181', 37.555914, 126.628034,
        '공항철도 청라국제도시 역 바로 앞 위치. 최고의 접근성. 입점사 한정 급여 거래은행을 하나은행으로 계약 시, 하나은행 수수료 면제.',
        'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzA1MjBfODQg%2FMDAxNDk1MjQ0OTAzNjQ5.23v57ZMFgY9Ltosw_G2VYlpd0usIYTOjjo3PJjqym2Ag.gnDtiH25fyxppFdHf6dDpt8xSkR-bWzrTT-o9w2MSUcg.JPEG.aviator2865%2FDSC03742.JPG&type=sc960_832',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('송도국제도시 점', '인천', '연수구', '인천타워대로 241', 37.392217, 126.634388, '입주사 대상 1~3층에 입점한 한의원 침 치료 한정 10%할인',
        'https://www.focusincheon.com/news/photo/202212/2108_2864_154.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('제주공항 점','제주', '제주시', '서해안로 624', 33.516796, 126.503541, '제주도 해안가에 위치해 경치가 좋음.',
        'https://image.goodchoice.kr/affiliate/2023/09/20/650a8707c9e5e.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('중앙로 점', '대구', '중구', '국채보상로 586 교보생명 빌딩', 35.870494, 128.594926, '중앙로 역과 도보 3분거리. 출퇴근에 편리.',
        'https://www.yeongnam.com/mnt/file/200503/20050324.010101339190002i1.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-meetingroom.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide06.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('라페스타 점', '경기', '고양시 일산동구', '', 123, 123, '',
        '라운지 공용 커피머신 외 입주사 사무실 마다 네스프레소 캡슐 커피 머신 제공.',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide05.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide06.jpg');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES ('판교역 점', '경기', '성남시 분당구', '판교역로 166', 37.395194, 127.110458, '판교역 바로 앞에 위치해 접근성이 좋고, IT 사업체가 모인 곳에 위치해 타회사와의 대면 미팅 시 편리.',
        'https://dimg.donga.com/wps/NEWS/IMAGE/2022/10/24/116112232.1.jpg',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-focusroom.webp');
INSERT INTO tbl_store (store_name, store_city, store_gu, store_address, latitude, longitude, description, store_thumbnail, store_img1, store_img2, store_img3)
VALUES
    ('수원화성 점', '경기', '수원시 장안구', '정조로 825', 37.282575, 127.015819,
        '건물 2층에 입점한 안과에서 입주사 직원 한정 무료 시력검진 진행 중.',
        'https://cdn.hitnews.co.kr/news/photo/202403/52804_70142_406.png',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-sofa.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/2502/average-bartable.webp',
        'https://sparkplus20241.cafe24.com/sparkpluskr/lounge/sec-lounge_picture-slide06.jpg');

INSERT INTO tbl_office (store_code, office_type, office_num, office_price, office_thumbnail)
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
(1, 1, '2025-03-15 08:00:00', '2025-03-15 10:00:00', 500000, '예약완료'),
(2, 4, '2025-03-18 10:00:00', '2025-03-18 12:00:00', 4000000, '예약완료'),
(3, 6, '2025-03-20 12:00:00', '2025-03-20 14:00:00', 1600000, '예약완료'),
(3, 9, '2025-03-20 14:00:00', '2025-03-20 16:00:00', 500000, '예약완료'),
(4, 7, '2025-03-22 18:00:00', '2025-03-22 20:00:00', 4500000, '예약완료'),
(1, 3, '2025-03-25 16:00:00', '2025-03-25 18:00:00', 500000, '예약취소'),
(2, 10, '2025-03-27 12:00:00', '2025-03-27 14:00:00', 4000000, '예약완료'),
(2, 12, '2025-03-27 14:00:00', '2025-03-27 16:00:00', 1550000, '예약완료'),
(2, 15, '2025-03-27 16:00:00', '2025-03-27 18:00:00', 500000, '예약완료'),
(4, 5, '2025-04-01 08:00:00', '2025-04-01 10:00:00', 1500000, '예약완료'),
(3, 8, '2025-04-05 10:00:00', '2025-04-05 12:00:00', 500000, '예약취소'),
(3, 11, '2025-04-05 12:00:00', '2025-04-05 14:00:00', 1500000, '예약취소');

INSERT INTO tbl_payment (reservation_code, payment_date, payment_method, payment_amount, payment_status)
VALUES
    (1, '2025-03-15 07:50:00', '카드', 500000, '결제완료'),
    (2, '2025-03-18 09:50:00', '계좌이체', 4000000, '결제완료'),
    (3, '2025-03-20 11:50:00', '카드', 2100000, '결제완료'),
    (5, '2025-03-22 17:50:00', '카드', 4500000, '결제완료'),
    (6, '2025-03-25 15:50:00', '계좌이체', 500000, '결제취소'),
    (7, '2025-03-27 11:50:00', '카드', 6050000, '결제완료'),
    (10, '2025-04-01 07:50:00', '계좌이체', 1500000, '결제완료'),
    (11, '2025-04-05 09:50:00', '카드', 2000000, '결제취소');

INSERT INTO tbl_reservation_payment (reservation_code, payment_code)
VALUES
(1, 1), -- 예약 1번 -> 결제 1번
(2, 2), -- 예약 2번 -> 결제 2번
(3, 3), -- 예약 3번 -> 결제 3번
(4, 3), -- 예약 4번 -> 결제 3번
(5, 4), -- 예약 5번 -> 결제 4번
(6, 5), -- 예약 6번 -> 결제 5번 (취소됨)
(7, 6), -- 예약 7번 -> 결제 6번
(8, 6), -- 예약 8번 -> 결제 6번
(9, 6), -- 예약 9번 -> 결제 6번
(10, 7), -- 예약 10번 -> 결제 7번
(11, 8), -- 예약 11번 -> 결제 8번 (취소됨)
(12, 8); -- 예약 12번 -> 결제 8번 (취소됨)

COMMIT;