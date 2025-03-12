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

-- officedb로 이동
use officedb;*/

-- --------------------------------------------------------
-- ddl
-- -------------------------------------------------------