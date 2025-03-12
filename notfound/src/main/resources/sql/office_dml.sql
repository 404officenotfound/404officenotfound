-- 정상협 : tbl_member 테이블에 데이터 삽입
INSERT INTO tbl_member
(member_name, member_id, member_password, member_email, member_phone, member_enddate, member_endstatus)
VALUES
    ('홍길동', 'hong123', 'password123', 'hong@example.com', '010-1234-5678', NULL, 'N'),
    ('김철수', 'kim123', 'password456', 'kim@example.com', '010-9876-5432', NULL, 'N'),
    ('이영희', 'lee123', 'password789', 'lee@example.com', '010-1122-3344', NULL, 'N'),
    ('박지은', 'park123', 'password000', 'park@example.com', '010-2233-4455', NOW(), 'Y');

-- 정상협 : tbl_authority 테이블에 데이터 삽입
INSERT INTO tbl_authority
(authority_code, authority_name)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

-- 정상협 : tbl_member_role 테이블에 데이터 삽입
INSERT INTO tbl_member_role
(member_code, authority_code)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 2);

COMMIT;