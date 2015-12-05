
-- 테이블 생성 순서 : member -> member_category -> main_category ->  product -> item -> 나머지


drop table qnaboard cascade constraint;
create table qnaboard(
	no number primary key,
	member_id varchar2(100) not null,
	title varchar2(100) not null,
	writer varchar2(100) not null,
	content CLOB not null,
	hit number default 0,
	time_post DATE not null,
	CONSTRAINT member_id foreign KEY(member_id) references member(member_id)
);

create sequence qna_seq;