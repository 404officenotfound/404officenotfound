-- use officedb

-- 1) 테이블 생성
DROP TABLE IF EXISTS tbl_member_role CASCADE;
DROP TABLE IF EXISTS tbl_member CASCADE;
DROP TABLE IF EXISTS tbl_authority CASCADE;



-- 정상협 : tbl_member(회원정보) 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_member
(
    -- COLUMN LEVEL CONSTRAINTS
    member_code INT AUTO_INCREMENT COMMENT '회원번호',
    member_name VARCHAR(20) NOt NULL COMMENT '이름',
    member_id VARCHAR(20) NOT NULL COMMENT '아이디',
    member_password VARCHAR(20) NOT NULL COMMENT '비밀번호',
    member_email VARCHAR (20) NOT NUll COMMENT '이메일',
    member_phone VARCHAR (20) NOT NULL COMMENT '전화번호',
    member_enrolldate VARCHAR (30) NOT NULL COMMENT '가입날짜',
    member_enddate VARCHAR (30) NOT NUll COMMENT '탈퇴날짜',
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