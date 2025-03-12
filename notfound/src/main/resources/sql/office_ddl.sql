-- --------------------------------------------------------
-- db 생성 및 유저 권한 할당
-- --------------------------------------------------------
/*-- 1) 새로운 officenotfound 계정 만들기
create user 'officenotfound'@'%' identified by 'officenotfound';
-- 'localhost'대신 '%'(와일드카드 패턴)를 사용하면 외부 IP에서도 접근 가능하다.

-- 현재 존재하는 데이터베이스 확인
show databases;

-- mysql 데이터베이스로 이동.
use mysql;

select * from user;
select User from user;

-- 2) 데이터베이스 생성 후 계정에 권한 부여
-- officedb라는 이름으로 데이터베이스(=스키마) 생성.
create database officedb;

-- 왼쪽 Navigator를 새로고침해서 officedb database(schema)가 추가된 것을 확인한다.
-- MySQL은 개념적으로 database와 schema를 구분하지 않는다.
-- (CREATE DATABASE와 CREATE SCHEMA가 같은 개념이다.)

-- officedb 스키마 및 하위에 대해 모든 권한을 부여
grant all privileges on officedb.* to 'officenotfound'@'%';

show grants for 'officenotfound'@'%';
-- use officedb

  -- officedb로 이동
use officedb;*/


-- 1) 테이블 생성
DROP TABLE IF EXISTS tbl_member_role CASCADE;
DROP TABLE IF EXISTS tbl_member CASCADE;
DROP TABLE IF EXISTS tbl_authority CASCADE;
DROP TABLE IF EXISTS tbl_inquiry CASCADE;
DROP TABLE IF EXISTS tbl_review CASCADE;
DROP TABLE IF EXISTS tbl_faq CASCADE;
DROP TABLE IF EXISTS tbl_inquiry CASCADE;



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




-- 이슬기 : 이벤트 테이블 (tbl_event)
CREATE TABLE tbl_event (
    event_code INT PRIMARY KEY AUTO_INCREMENT COMMENT '이벤트 번호',
    authority_code INT NOT NULL COMMENT '권한번호',
    event_title VARCHAR(50) NOT NULL COMMENT '제목',
    event_content VARCHAR(255) NOT NULL COMMENT '내용',
    event_img VARCHAR(255) NOT NULL COMMENT '이미지 파일 경로',
    event_status VARCHAR(10) NOT NULL COMMENT '진행 상태'
);


-- 이슬기 : 리뷰 테이블 (tbl_review)
CREATE TABLE tbl_review (
    review_code INT PRIMARY KEY AUTO_INCREMENT COMMENT '리뷰 번호',
    member_code INT NOT NULL COMMENT '회원번호',
    payment_code INT NOT NULL COMMENT '결제번호',
    member_id VARCHAR(20) NOT NULL COMMENT '아이디',
    review_title VARCHAR(50) NOT NULL COMMENT '제목',
    review_content VARCHAR(255) NOT NULL COMMENT '내용',
    review_created_at VARCHAR(30) NOT NULL COMMENT '등록일자',
    review_rating TINYINT NOT NULL COMMENT '평점',
    review_image VARCHAR(255) NULL COMMENT '이미지 파일 경로',
    FOREIGN KEY (member_code) REFERENCES tbl_member(member_code) ON DELETE CASCADE,
    FOREIGN KEY (payment_code) REFERENCES tbl_payment(payment_code) ON DELETE CASCADE
);

-- 이슬기 : FAQ 테이블 (tbl_faq)
CREATE TABLE tbl_faq (
    faq_code INT PRIMARY KEY AUTO_INCREMENT COMMENT 'FAQ 번호',
    faq_title VARCHAR(50) NOT NULL COMMENT '제목',
    faq_content VARCHAR(255) NOT NULL COMMENT '내용'
);


-- 이슬기 : 1:1 문의 테이블 (tbl_inquiry)
CREATE TABLE tbl_inquiry (
    inquiry_code INT PRIMARY KEY AUTO_INCREMENT COMMENT '문의글 번호',
    member_code INT NOT NULL COMMENT '회원번호',
    member_id VARCHAR(20) NOT NULL COMMENT '아이디',
    inquiry_title VARCHAR(50) NOT NULL COMMENT '제목',
    inquiry_content VARCHAR(255) NOT NULL COMMENT '내용',
    inquiry_created_at VARCHAR(30) NOT NULL COMMENT '등록일자',
    inquiry_answer_state VARCHAR(10) NOT NULL COMMENT '답변 상태',
    inquiry_admin_answer VARCHAR(255) NULL COMMENT '관리자 답변',
    CONSTRAINT fk_inquiry_member FOREIGN KEY (member_code) REFERENCES tbl_member (member_code) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='1:1 문의 게시판';



-- --------------------------------------------------------
-- ddl
-- -------------------------------------------------------

