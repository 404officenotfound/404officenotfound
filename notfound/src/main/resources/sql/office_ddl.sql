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
DROP TABLE IF EXISTS tbl_member_role CASCADE;
DROP TABLE IF EXISTS tbl_member CASCADE;
DROP TABLE IF EXISTS tbl_authority CASCADE;
DROP TABLE IF EXISTS tbl_store;
DROP TABLE IF EXISTS tbl_office;


-- 정상협 : tbl_member(회원정보) 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_member
(
    -- COLUMN LEVEL CONSTRAINTS
    member_code INT AUTO_INCREMENT COMMENT '회원번호',
    member_name VARCHAR(20) NOt NULL COMMENT '이름',
    member_id VARCHAR(20) NOT NULL COMMENT '아이디',
    member_password VARCHAR(255) NOT NULL COMMENT '비밀번호',
    member_email VARCHAR (20) NOT NUll COMMENT '이메일',
    member_phone VARCHAR (20) NOT NULL COMMENT '전화번호',
    member_enddate VARCHAR (30) NUll COMMENT '탈퇴날짜',
    member_endstatus VARCHAR (20) NOT NULL DEFAULT 'N' COMMENT '탈퇴여부',
    -- TABLE LEVEL CONSTRAINTS
    CONSTRAINT pk_category_code PRIMARY KEY (member_code)
    ) ENGINE=INNODB COMMENT '회원정보';


-- 정상협 : tbl_authority(권한) 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_authority
(
    -- COLUMN LEVEL CONSTRAINTS
    authority_code INT AUTO_INCREMENT COMMENT '권한번호',
    authority_name VARCHAR(20) NOT NULL COMMENT '권한이름',
    -- TABLE LEVEL CONSTRAINTS
    CONSTRAINT pk_authority_code PRIMARY KEY (authority_code)
    ) ENGINE=INNODB COMMENT '권한';


-- 정상협 : tbl_member_role(회원별권한) 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_member_role
(
    -- COLUMN LEVEL CONSTRAINTS
    member_code INT AUTO_INCREMENT COMMENT '회원번호',
    authority_code INT NOT NULL COMMENT '권한번호',
    -- TABLE LEVEL CONSTRAINTS
    CONSTRAINT pk_member_role PRIMARY KEY (member_code, authority_code),
    CONSTRAINT fk_member_code FOREIGN KEY (member_code) REFERENCES tbl_member (member_code),
    CONSTRAINT fk_authority_code FOREIGN KEY (authority_code) REFERENCES tbl_authority (authority_code)
    ) ENGINE=INNODB COMMENT '회원별권한';


-- 양서진(지점 테이블)
CREATE TABLE IF NOT EXISTS tbl_store (
    store_code BIGINT(5) AUTO_INCREMENT COMMENT '해당지점 식별번호',
#     authority_code INT NOT NULL COMMENT '권한 번호';
    store_name VARCHAR(30) NOT NULL COMMENT '지점 이름',
    store_city VARCHAR(30) NOT NULL COMMENT '주소(시도)',
    store_gu VARCHAR(30) NOT NULL COMMENT '주소(지역구)',
    store_address VARCHAR(255) NOT NULL COMMENT '상세 주소',
    longitude DOUBLE NOT NULL COMMENT '지도 경도',
    latitude DOUBLE NOT NULL COMMENT '지점 위도',
    description VARCHAR(255) NOT NULL COMMENT '지점 소개',
    store_thumbnail VARCHAR(255) NOT NULL COMMENT '지점썸네일 URL',
    store_img1 VARCHAR(255) NULL COMMENT '공용공간1사진 URL',
    store_img2 VARCHAR(255) NULL COMMENT '공용공간2사진 URL',
    store_img3 VARCHAR(255) NULL COMMENT '공용공간3사진 URL',
    CONSTRAINT pk_store_code PRIMARY KEY (store_code),
#     CONSTRAINT fk_authority_code FOREIGN KEY (authority_code) REFFERENCES tbl_authority (authority_code)
) ENGINE=INNODB COMMENT '지점';

-- 양서진(사무실 테이블)
CREATE TABLE IF NOT EXISTS tbl_office (
    office_code BIGINT(10) NOT NULL COMMENT '사무실 식별번호',
    store_code BIGINT(5) NOT NULL COMMENT '해당지점 식별번호',
    office_type VARCHAR(30) NOT NULL COMMENT '사무실 유형',
    office_num INT NOT NULL COMMENT '사무실 호실',
    office_price INT NOT NULL COMMENT '사무실 가격',
    office_thumbnail VARCHAR(255) NOT NULL COMMENT '사무실 사진 URL',
    CONSTRAINT pk_office_code PRIMARY KEY (office_code),
    CONSTRAINT fk_store_code FOREIGN KEY (store_code) REFERENCES tbl_store (store_code)
) ENGINE=INNODB COMMENT '사무실';