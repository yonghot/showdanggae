
-- 테이블 생성 순서 : member -> main_category -> member_category ->  product -> item -> 나머지

drop table member cascade constraint;  -- 제약조건 있는 테이블은 이렇게 삭제...하면 제약조건들이 없어져서 되는거다
create table member(
	member_id varchar2(100) primary key,
	password varchar2(100) not null,
	member_name varchar2(100) not null,
	email varchar2(100) not null,
	birthday DATE not null,
	report number default 0
);


drop table main_category cascade constraint;
create table main_category (
	category varchar2(100) primary key
);


drop table member_category cascade constraint;
create table member_category (
	category_id number primary key,
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_category_main foreign key(category) references main_category(category),
	constraint fk_category_member_id foreign key(member_id) references member(member_id)
);


drop table product cascade constraint;
create table product (
	product_id number primary key,
	category_id number not null,
	member_id varchar2(100) not null,
	product_name varchar2(100) not null,
	likes number default 0,
	dislikes number default 0,
	hits number default 0,
	review clob not null,
	review_score number default 0,
	detail clob not null,
	visiblity number default 0,
	regist_date date not null,
	constraint fk_product_category_id foreign key(category_id) references member_category(category_id),
	constraint fk_product_member_id foreign key(member_id) references member(member_id)
);

drop table item cascade constraint;
create table item (
	item varchar2(100) primary key
);


drop table seller_link cascade constraint;
create table seller_link (
	link varchar2(100) primary key,
	product_id number not null,
	price number not null,
	constraint fk_seller_link_product_id foreign key(product_id) references product(product_id)
);


drop table eval_item cascade constraint;
create table eval_item (
	item varchar2(100) primary key,
	product_id number not null,
	item_point number default 0,
	constraint fk_eval_item_item foreign key(item) references item(item),
	constraint fk_eval_itemt_product_id foreign key(product_id) references product(product_id)
);


drop table noticeboard;
create table noticeboard(
	no number primary key,
	TITLE varchar2(100) not null,
	writer varchar2(100) not null,
	password varchar2(100) not null,
	content CLOB not null,
	hit number default 0,
	time_post DATE not null
);

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


drop table message cascade constraint;
create table message (
	mno number primary key,
	member_id varchar2(100),
	message CLOB not null,
	spand_name varchar2(100) not null,
	title varchar2(100) not null,
	spand_date DATE not null,
	read number default 0,
	constraint fk_message_member_id foreign KEY(member_id) references member(member_id)
);


drop table follow cascade constraint;
create table follow(
	following_date DATE not null,
	following varchar2(100) not null,
	follower varchar2(100) not null,
	constraint fk_following_member_id foreign key(following) references member(member_id),
	constraint fk_follower_member_id foreign key(follower) references member(member_id),
	constraint pk_follow primary key(following, follower)
);


drop table interest cascade constraint;
create table interest (
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_interest_main foreign key(category) references main_category(category),
	constraint fk_interest_member_id foreign key(member_id) references member(member_id),
	constraint pk_interest primary key (category, member_id)
);


-- 총 12개 테이블
------------------------------------------------------------------------------------------------------------

drop sequence product_seq;
drop sequence member_category_seq; 
drop sequence message_seq;
drop sequence notice_seq;
drop sequence qna_seq;

create sequence product_seq;
create sequence member_category_seq; 
create sequence message_seq;
create sequence notice_seq;
create sequence qna_seq;

------------------------------------------------------------------------------------------------------------

insert into member(member_id, password, member_name, email, birthday) values('java','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member(member_id, password, member_name, email, birthday) values('java1','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member(member_id, password, member_name, email, birthday) values('java2','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member(member_id, password, member_name, email, birthday) values('admingalbage','1234','관리자','admin@showdanggae.com', to_date('120204','RRMMDD')); 
insert into member(member_id, password, member_name, email, birthday) values('dd','dd','dd','asdsdsdin@showdanggae.com', to_date('120204','RRMMDD')); 
insert into member(member_id, password, member_name, email, birthday) values('admin','1234','dd','asdsdsdin@showdanggae.com', to_date('120204','RRMMDD')); 


insert into main_category(category) values('노트북');
insert into main_category(category) values('카메라');
insert into main_category(category) values('화장품');


insert into member_category(category_id, category, member_id) values('1', '노트북','java');
insert into member_category(category_id, category, member_id) values('2', '화장품','java');
insert into member_category(category_id, category, member_id) values('3', '노트북','dd');
insert into member_category(category_id, category, member_id) values('4', '노트북','dd');


insert into product(product_id, category_id, member_id, product_name, review, detail, regist_date) 
values (product_seq.nextval, '1', 'java', '기가바이트 P35', '비행기 이륙소리 개쩔', '가성비는 좋은데 내구성이 영..', sysdate); 
insert into product(product_id, category_id, member_id, product_name, review, detail, regist_date) 
values (product_seq.nextval, '2', 'java', '바이오더마 립글로즈', '발림성 갑', '좋은 참 좋은데 내 돈주고 사긴 좀 비쌈..', sysdate); 
insert into product(PRODUCT_ID, CATEGORY_ID, MEMBER_ID, PRODUCT_NAME, LIKES, DISLIKES, HITS, REVIEW, REVIEW_SCORE, DETAIL, VISIBLITY, REGIST_DATE) 
values(product_seq.nextval, '1', 'java', '가시갑옷', 0, 0, 0, '때려보삼', 0, '가시가 촘촘합디다', 0, sysdate);

insert into interest(category, member_id) values('노트북','java');
insert into interest(category, member_id) values('화장품','java');
insert into interest(category, member_id) values('노트북','dd');


insert into item(item) values('가성비');
insert into item(item) values('디자인');
insert into item(item) values('AS');


insert into seller_link(link, product_id, price) values('www.naver.com', '1', '1200000');
insert into seller_link(link, product_id, price) values('www.daum.net', '2', '20000');


insert into eval_item(item, product_id, item_point) values('가성비', '1', '8');
insert into eval_item(item, product_id, item_point) values('디자인', '1', '4');
insert into eval_item(item, product_id, item_point) values('AS', '1', '2');


insert into noticeboard(no,TITLE,writer,password,content,time_post) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);
insert into noticeboard(no,TITLE,writer,password,content,time_post) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);
insert into noticeboard(no,TITLE,writer,password,content,time_post) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);
insert into noticeboard(no,TITLE,writer,password,content,time_post) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);
insert into noticeboard(no,TITLE,writer,password,content,time_post) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);


insert into qnaboard(no, member_id, title, writer, content, time_post) 
values(qna_seq.nextval, 'java','이 서비스는 도대체','김용호', '언제 개발 완료 되는거죠?', SYSDATE);
insert into qnaboard(no, member_id, title, writer, content, time_post) 
values(qna_seq.nextval, 'java','이 서비스는 도대체','김용호', '언제 개발 완료 되는거죠?', SYSDATE);
insert into qnaboard(no, member_id, title, writer, content, time_post) 
values(qna_seq.nextval, 'java','이 서비스는 도대체','김용호', '언제 개발 완료 되는거죠?', SYSDATE);
insert into qnaboard(no, member_id, title, writer, content, time_post) 
values(qna_seq.nextval, 'java','이 서비스는 도대체','김용호', '언제 개발 완료 되는거죠?', SYSDATE);
insert into qnaboard(no, member_id, title, writer, content, time_post) 
values(qna_seq.nextval, 'java','이 서비스는 도대체','김용호', '언제 개발 완료 되는거죠?', SYSDATE);
insert into qnaboard(no, member_id, title, writer, content, time_post) 
values(qna_seq.nextval, 'java','이 서비스는 도대체','김용호', '언제 개발 완료 되는거죠?', SYSDATE);


INSERT INTO MESSAGE(member_Id,message,spand_name,title,spand_date,mno)
values ('admin', '두번째메세지TEST', '관리자', 'TEST1', SYSDATE, message_seq.nextval);
INSERT INTO MESSAGE(member_Id,message,spand_name,title,spand_date,mno)
values ('admin', '두번째메세지TEST', '관리자', 'TEST1', SYSDATE, message_seq.nextval);
INSERT INTO MESSAGE(member_Id,message,spand_name,title,spand_date,mno)
values ('admin', '두번째메세지TEST', '관리자', 'TEST1', SYSDATE, message_seq.nextval);


insert into follow(following_date,following,follower) values(sysdate,'java1','java');
