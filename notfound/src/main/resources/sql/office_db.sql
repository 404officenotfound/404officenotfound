-- 1) 디폴트 데이터베이스 스키마인 mysql로 이동
USE mysql;

#SELECT * FROM user;
SHOW databases;

-- 2) 데이터베이스 생성(officedb)
CREATE DATABASE officedb;
SHOW DATABASES;

-- 3) 유저 생성 (notfound/notfound)
CREATE USER 'officenotfound'@'%' IDENTIFIED BY 'officenotfound';
SELECT * FROM user;


-- 4) 유저에게 권한 부여
GRANT ALL PRIVILEGES ON officedb.* TO 'officenotfound'@'%';
SHOW GRANTS FOR 'officenotfound'@'%';


-- 5) SQL을 실행할 타겟 스키마(officedb)로 이동
USE officedb;