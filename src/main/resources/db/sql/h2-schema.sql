-- 1. Contents 테이블 (과제 명세서와 완벽 일치)
create table contents
(
    id                 bigint primary key      not null auto_increment,
    title              varchar(100)            not null,
    description        text,
    view_count         bigint        default 0 not null,
    created_by         varchar(50)             not null,
    created_date       timestamp default now() not null,
    last_modified_by   varchar(50),
    last_modified_date timestamp
);

-- 2. Member 테이블 (Spring Security 호환성 향상)
CREATE TABLE member (
    member_id    VARCHAR(50) PRIMARY KEY,       
    member_pw    VARCHAR(255) NOT NULL,         
    member_level VARCHAR(20) DEFAULT 'ROLE_USER' NOT NULL, -- 길이를 살짝 늘리고 기본값 변경
    member_join  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    member_login TIMESTAMP,
    CHECK (member_level IN ('ROLE_USER', 'ROLE_ADMIN')) -- ROLE_ 접두사 추가
);