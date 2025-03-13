-- --------------------------------------------------------
-- db 생성 및 유저 권한 할당
-- --------------------------------------------------------
# -- 1) 새로운 officenotfound 계정 만들기
# create user 'officenotfound'@'%' identified by 'officenotfound';
# -- 'localhost'대신 '%'(와일드카드 패턴)를 사용하면 외부 IP에서도 접근 가능하다.
#
# -- 현재 존재하는 데이터베이스 확인
# show databases;
#
# -- mysql 데이터베이스로 이동.
# use mysql;
#
# select * from user;
# select User from user;
#
# -- 2) 데이터베이스 생성 후 계정에 권한 부여
# -- officedb라는 이름으로 데이터베이스(=스키마) 생성.
# create database officedb;
#
# -- 왼쪽 Navigator를 새로고침해서 officedb database(schema)가 추가된 것을 확인한다.
# -- MySQL은 개념적으로 database와 schema를 구분하지 않는다.
# -- (CREATE DATABASE와 CREATE SCHEMA가 같은 개념이다.)
#
# -- officedb 스키마 및 하위에 대해 모든 권한을 부여
# grant all privileges on officedb.* to 'officenotfound'@'%';
#
# show grants for 'officenotfound'@'%';
#
# -- officedb로 이동
# use officedb;

-- --------------------------------------------------------
-- ddl
-- --------------------------------------------------------

-- 1) 테이블 생성
DROP TABLE IF EXISTS tbl_review CASCADE;
DROP TABLE IF EXISTS tbl_inquiry CASCADE;
DROP TABLE IF EXISTS tbl_faq CASCADE;
DROP TABLE IF EXISTS tbl_event CASCADE;
DROP TABLE IF EXISTS tbl_reservation_payment CASCADE;
DROP TABLE IF EXISTS tbl_payment CASCADE;
DROP TABLE IF EXISTS tbl_reservation CASCADE;
DROP TABLE IF EXISTS tbl_office CASCADE;
DROP TABLE IF EXISTS tbl_store CASCADE;
DROP TABLE IF EXISTS tbl_member_role CASCADE;
DROP TABLE IF EXISTS tbl_authority CASCADE;
DROP TABLE IF EXISTS tbl_member CASCADE;

-- 1. 회원정보 테이블 (tbl_member)
CREATE TABLE IF NOT EXISTS tbl_member (
                                          member_code INT AUTO_INCREMENT COMMENT '회원번호',
                                          member_name VARCHAR(20) NOT NULL COMMENT '이름',
                                          member_id VARCHAR(20) NOT NULL COMMENT '아이디',
                                          member_password VARCHAR(255) NOT NULL COMMENT '비밀번호',
                                          member_email VARCHAR(20) NOT NULL COMMENT '이메일',
                                          member_phone VARCHAR(20) NOT NULL COMMENT '전화번호',
                                          member_enddate DATETIME NULL COMMENT '탈퇴날짜',
                                          member_endstatus VARCHAR(20) NOT NULL DEFAULT 'N' COMMENT '탈퇴여부',
                                          CONSTRAINT pk_member_code PRIMARY KEY (member_code)
) ENGINE=INNODB COMMENT '회원정보';

-- 2. 권한 테이블 (tbl_authority)
CREATE TABLE IF NOT EXISTS tbl_authority (
                                             authority_code INT NOT NULL COMMENT '권한번호',
                                             authority_name VARCHAR(20) NOT NULL COMMENT '권한이름',
                                             CONSTRAINT pk_authority_code PRIMARY KEY (authority_code)
) ENGINE=INNODB COMMENT '권한';

-- 3. 회원별권한 테이블 (tbl_member_role)
CREATE TABLE IF NOT EXISTS tbl_member_role (
                                               member_code INT NOT NULL COMMENT '회원번호',
                                               authority_code INT NOT NULL COMMENT '권한번호',
                                               CONSTRAINT pk_member_role PRIMARY KEY (member_code, authority_code),
                                               CONSTRAINT fk_member_code FOREIGN KEY (member_code) REFERENCES tbl_member (member_code),
                                               CONSTRAINT fk_authority_code FOREIGN KEY (authority_code) REFERENCES tbl_authority (authority_code)
) ENGINE=INNODB COMMENT '회원별권한';

-- 4. 지점 테이블 (tbl_store)
CREATE TABLE IF NOT EXISTS tbl_store (
                                         store_code INT AUTO_INCREMENT COMMENT '해당지점 식별번호',
                                         store_name VARCHAR(30) NOT NULL COMMENT '지점 이름',
                                         store_city VARCHAR(30) NOT NULL COMMENT '주소(시도)',
                                         store_gu VARCHAR(30) NOT NULL COMMENT '주소(지역구)',
                                         store_address VARCHAR(255) NOT NULL COMMENT '상세 주소',
                                         latitude DOUBLE NOT NULL COMMENT '지점 위도',
                                         longitude DOUBLE NOT NULL COMMENT '지도 경도',
                                         description VARCHAR(255) NOT NULL COMMENT '지점 소개',
                                         store_thumbnail VARCHAR(255) NOT NULL COMMENT '지점썸네일 URL',
                                         store_img1 VARCHAR(255) NULL COMMENT '공용공간1사진 URL',
                                         store_img2 VARCHAR(255) NULL COMMENT '공용공간2사진 URL',
                                         store_img3 VARCHAR(255) NULL COMMENT '공용공간3사진 URL',
                                         CONSTRAINT pk_store_code PRIMARY KEY (store_code)
) ENGINE=INNODB COMMENT '지점';

-- 5. 사무실 테이블 (tbl_office)
CREATE TABLE IF NOT EXISTS tbl_office (
                                          office_code INT AUTO_INCREMENT NOT NULL COMMENT '사무실 식별번호',
                                          store_code INT NOT NULL COMMENT '해당지점 식별번호',
                                          office_type VARCHAR(30) NOT NULL COMMENT '사무실 유형',
                                          office_num INT NOT NULL COMMENT '사무실 호실',
                                          office_price INT NOT NULL COMMENT '사무실 가격(2시간)',
                                          office_thumbnail VARCHAR(255) NOT NULL COMMENT '사무실 사진 URL',
                                          CONSTRAINT pk_office_code PRIMARY KEY (office_code),
                                          CONSTRAINT fk_store_code FOREIGN KEY (store_code) REFERENCES tbl_store (store_code)
) ENGINE=INNODB COMMENT '사무실';

-- 6. 예약 테이블 (tbl_reservation)
CREATE TABLE IF NOT EXISTS tbl_reservation (
                                               reservation_code INT AUTO_INCREMENT COMMENT '예약식별코드',
                                               member_code INT NOT NULL COMMENT '회원식별코드',
                                               office_code INT NOT NULL COMMENT '사무실식별코드',
                                               reservation_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '예약일시',
                                               start_datetime DATETIME NOT NULL COMMENT '이용시작일시',
                                               end_datetime DATETIME NOT NULL COMMENT '이용종료일시',
                                               reservation_status VARCHAR(20) DEFAULT '예약완료' COMMENT '예약상태',
                                               total_price INT NOT NULL COMMENT '총 결제금액',
                                               PRIMARY KEY (reservation_code),
                                               FOREIGN KEY (member_code) REFERENCES tbl_member(member_code),
                                               INDEX idx_reservation_member_id (member_code),
                                               INDEX idx_reservation_date (reservation_date)
) ENGINE=InnoDB COMMENT '예약';

-- 7. 결제 테이블 (tbl_payment)
CREATE TABLE IF NOT EXISTS tbl_payment (
                                           payment_code INT AUTO_INCREMENT COMMENT '결제식별코드',
                                           reservation_code INT NOT NULL COMMENT '예약식별코드',
                                           payment_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '결제일시',
                                           payment_method VARCHAR(50) NOT NULL COMMENT '결제수단',
                                           payment_amount INT NOT NULL COMMENT '결제금액',
                                           payment_status VARCHAR(20) DEFAULT '결제완료' COMMENT '결제상태',
                                           PRIMARY KEY (payment_code),
                                           FOREIGN KEY (reservation_code) REFERENCES tbl_reservation(reservation_code)
) ENGINE=InnoDB COMMENT '결제';

-- 8. 예약별 결제 테이블 (tbl_reservation_payment)
CREATE TABLE IF NOT EXISTS tbl_reservation_payment (
                                                       reservation_payment_code INT AUTO_INCREMENT COMMENT '예약-결제 연결 코드',
                                                       payment_code INT NOT NULL COMMENT '결제식별코드',
                                                       reservation_code INT NOT NULL COMMENT '예약식별코드',
                                                       PRIMARY KEY (reservation_payment_code),
                                                       FOREIGN KEY (payment_code) REFERENCES tbl_payment(payment_code),
                                                       FOREIGN KEY (reservation_code) REFERENCES tbl_reservation(reservation_code),
                                                       INDEX idx_reservation_payment_payment_code (payment_code)
) ENGINE=InnoDB COMMENT '결제와 예약 연결 테이블';

-- 9. 이벤트 테이블 (tbl_event)
CREATE TABLE tbl_event (
                           event_code INT PRIMARY KEY AUTO_INCREMENT COMMENT '이벤트 번호',
                           event_title VARCHAR(50) NOT NULL COMMENT '제목',
                           event_content VARCHAR(255) NOT NULL COMMENT '내용',
                           event_img VARCHAR(255) NOT NULL COMMENT '이미지 파일 경로',
                           event_status VARCHAR(10) NOT NULL COMMENT '진행 상태'
);

-- 10. FAQ 테이블 (tbl_faq)
CREATE TABLE tbl_faq (
                         faq_code INT PRIMARY KEY AUTO_INCREMENT COMMENT 'FAQ 번호',
                         faq_title VARCHAR(50) NOT NULL COMMENT '제목',
                         faq_content VARCHAR(255) NOT NULL COMMENT '내용'
);

-- 11. 1:1 문의 테이블 (tbl_inquiry)
CREATE TABLE tbl_inquiry (
                             inquiry_code INT PRIMARY KEY AUTO_INCREMENT COMMENT '문의글 번호',
                             member_code INT NOT NULL COMMENT '회원번호',
                             inquiry_title VARCHAR(50) NOT NULL COMMENT '제목',
                             inquiry_content VARCHAR(255) NOT NULL COMMENT '내용',
                             inquiry_created_at VARCHAR(30) NOT NULL COMMENT '등록일자',
                             inquiry_answer_state VARCHAR(10) NOT NULL COMMENT '답변 상태',
                             inquiry_admin_answer VARCHAR(255) NULL COMMENT '관리자 답변',
                             FOREIGN KEY (member_code) REFERENCES tbl_member(member_code) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT '1:1 문의 게시판';

-- 12. 리뷰 테이블 (tbl_review)
CREATE TABLE tbl_review (
                            review_code INT PRIMARY KEY AUTO_INCREMENT COMMENT '리뷰 번호',
                            member_code INT NOT NULL COMMENT '회원번호',
                            payment_code INT NOT NULL COMMENT '결제번호',
                            review_content VARCHAR(255) NOT NULL COMMENT '내용',
                            review_rating TINYINT NOT NULL COMMENT '평점',
                            FOREIGN KEY (member_code) REFERENCES tbl_member(member_code) ON DELETE CASCADE,
                            FOREIGN KEY (payment_code) REFERENCES tbl_payment(payment_code) ON DELETE CASCADE
);
