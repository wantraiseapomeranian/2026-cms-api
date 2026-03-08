-- 테스트용 관리자 계정 생성 (아이디: admin, 비밀번호: 1234)
insert into member (member_id, member_pw, member_level, member_join) 
values ('admin', '{noop}1234', 'ROLE_ADMIN', now());

-- 테스트용 일반 사용자 계정 생성 (아이디: user1, 비밀번호: 1234)
insert into member (member_id, member_pw, member_level, member_join) 
values ('user1', '{noop}1234', 'ROLE_USER', now());

-- 테스트용 게시글 생성
insert into contents (title, description, view_count, created_by, created_date)
values ('첫 번째 테스트 게시글', '일반 사용자가 작성한 글입니다.', 0, 'user1', now());