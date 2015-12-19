
-- 테이블 생성 순서 : member -> main_category -> member_category ->  product -> item -> 나머지
drop table member cascade constraint;  -- 제약조건 있는 테이블은 이렇게 삭제...하면 제약조건들이 없어져서 되는거다
create table member(
	member_id varchar2(100) primary key,
	password varchar2(100) not null,
	member_name varchar2(100) not null,
	member_info varchar2(50) default null,
	email varchar2(100) not null,
	birthday date not null,
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
	constraint fk_category_main foreign key(category) references main_category(category) ON DELETE CASCADE,
	constraint fk_category_member_id foreign key(member_id) references member(member_id) ON DELETE CASCADE
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
	review clob,
	detail clob,
	review_score number default 0,
	visiblity number default 0,
	regist_date date not null,
	thumbnail_link clob not null,
	constraint fk_product_category_id foreign key(category_id) references member_category(category_id),
	constraint fk_product_member_id foreign key(member_id) references member(member_id)
);

drop table item cascade constraint;
create table item (
	item varchar2(100) primary key
);


drop table seller_link cascade constraint;
create table seller_link (
	link varchar2(300) not null,
	product_id number not null,
	category_id number not null,
	price number not null,
	constraint fk_seller_link_product_id foreign key(product_id) references product(product_id) ON DELETE CASCADE,
	constraint fk_seller_link_category_id foreign key(category_id) references member_category(category_id) ON DELETE CASCADE,
	constraint pk_seller_link primary key (link, product_id)
);

drop table eval_item cascade constraint;
create table eval_item (
	item varchar2(100) not null,
	product_id number not null,
	category_id number not null,
	item_point number default 0,
	constraint fk_eval_item_item foreign key(item) references item(item) ON DELETE CASCADE,
	constraint fk_eval_item_product_id foreign key(product_id) references product(product_id) ON DELETE CASCADE,
	constraint fk_eval_item_category_id foreign key(category_id) references member_category(category_id) ON DELETE CASCADE,
	constraint pk_eval_item primary key (item,product_id)
);


drop table noticeboard;
create table noticeboard(
	no number primary key,
	title varchar2(100) not null,
	writer varchar2(100) not null,
	content clob not null,
	hit number default 0,
	writedate date not null
);


drop table qnaboard cascade constraint;
create table qnaboard(
	no number primary key,
	title varchar2(100) not null,
	member_id varchar2(100) not null,
	writer varchar2(100) not null,
	content clob not null,
	writedate date not null,
	viewcount number default 0,
	ref number default 0, 
	restep number default 0, 
	relevel number default 0,
	total number default 0,
	constraint member_id foreign key(member_id) references member(member_id) ON DELETE CASCADE
);

drop table qnacomment cascade constraint;
create table qnacomment(
	cno number primary key,
	no number not null,
	member_name varchar2(100) not null,
	member_id varchar2(100) not null,
	replycomment clob not null,
	commentdate date not null,
	constraint no foreign key(no) references qnaboard(no) ON DELETE CASCADE,
	constraint fk_qnacomment foreign key(member_id) references member(member_id) ON DELETE CASCADE
);

drop table message cascade constraint;
create table message (
	mno number primary key,
	member_id varchar2(100),
	message clob not null,
	sender varchar2(100) not null,
	title varchar2(100) not null,
	send_date date not null,
	read number default 0,
	constraint fk_message_member_id foreign key(member_id) references member(member_id) ON DELETE CASCADE
);


drop table follow cascade constraint;
create table follow(
	following_date date not null,
	following varchar2(100) not null,
	follower varchar2(100) not null,
	constraint fk_following_member_id foreign key(following) references member(member_id) ON DELETE CASCADE,
	constraint fk_follower_member_id foreign key(follower) references member(member_id) ON DELETE CASCADE,
	constraint pk_follow primary key(following, follower)
);


drop table interest cascade constraint;
create table interest (
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_interest_main foreign key(category) references main_category(category)ON DELETE CASCADE ,
	constraint fk_interest_member_id foreign key(member_id) references member(member_id) ON DELETE CASCADE ,
	constraint pk_interest primary key (category, member_id) 
);


-- 총 12개 테이블
------------------------------------------------------------------------------------------------------------

drop sequence product_seq;
drop sequence member_category_seq; 
drop sequence message_seq;
drop sequence notice_seq;
drop sequence qna_seq;
drop sequence qnacomment_seq;

create sequence product_seq;
create sequence member_category_seq; 
create sequence message_seq;
create sequence notice_seq;
create sequence qna_seq;
create sequence qnacomment_seq;
------------------------------------------------------------------------------------------------------------

insert into member(member_id, password, member_name, member_info,email, birthday) values('java','1234','김용호','ㅋㅋ','blue@blueprint.com', to_date('880307','rrmmdd'));
insert into member(member_id, password, member_name, member_info,email, birthday) values('java1','1234','김용호','ㅋㅋ','blue@blueprint.com', to_date('880307','rrmmdd'));
insert into member(member_id, password, member_name, member_info,email, birthday) values('java2','1234','김용호','','blue@blueprint.com', to_date('880307','rrmmdd'));
insert into member(member_id, password, member_name, member_info,email, birthday) values('admingalbage','1234','관리자','관리자','admin@showdanggae.com', to_date('120204','rrmmdd')); 
insert into member(member_id, password, member_name, member_info,email, birthday) values('dd','dd','dd','ㅋㅋ','asdsdsdin@showdanggae.com', to_date('120204','rrmmdd')); 
insert into member(member_id, password, member_name, member_info,email, birthday) values('admin','1234','dd','ㅋㅇㄹ','asdsdsdin@showdanggae.com', to_date('120204','rrmmdd')); 
insert into member(member_id, password, member_name, member_info,email, birthday) values('lipchel','1234','유서정','안녕안녕','lipchel@naver.com', to_date('880307','rrmmdd'));

insert into main_category(category) values('노트북');
insert into main_category(category) values('카메라');
insert into main_category(category) values('화장품');




insert into interest(category, member_id) values('노트북','java');
insert into interest(category, member_id) values('화장품','java');
insert into interest(category, member_id) values('노트북','dd');
insert into interest(category, member_id) values('노트북','lipchel');
insert into interest(category, member_id) values('화장품','lipchel');

insert into item(item) values('가성비');
insert into item(item) values('디자인');
insert into item(item) values('as');


insert into noticeboard(no,title,writer,content,writedate) 
values(notice_seq.nextval,'공지사항입니다','관리자','방가방가',sysdate);
insert into noticeboard(no,title,writer,content,writedate)
values(notice_seq.nextval,'공지사항입니다','관리자','방가방가',sysdate);
insert into noticeboard(no,title,writer,content,writedate) 
values(notice_seq.nextval,'공지사항입니다','관리자','방가방가',sysdate);
insert into noticeboard(no,title,writer,content,writedate) 
values(notice_seq.nextval,'공지사항입니다','관리자','방가방가',sysdate);
insert into noticeboard(no,title,writer,content,writedate) 
values(notice_seq.nextval,'공지사항입니다','관리자','방가방가',sysdate);
insert into noticeboard(no,title,writer,content,writedate) 
values(notice_seq.nextval,'공지사항입니다','관리자','방가방가',sysdate);



insert into message(member_id,message,sender,title,send_date,mno)
values ('java', '두번째메세지test', '관리자', 'test1', sysdate, message_seq.nextval);
insert into message(member_id,message,sender,title,send_date,mno)
values ('java', '두번째메세지test', '관리자', 'test1', sysdate, message_seq.nextval);
insert into message(member_id,message,sender,title,send_date,mno)
values ('java', '두번째메세지test', '관리자', 'test1', sysdate, message_seq.nextval);


insert into follow(following_date,following,follower) values(sysdate,'java1','java');



----팔로우 임의 데이터


insert into follow(following_date,following,follower)
values(sysdate,'lipchel','dd');

insert into follow(following_date,following,follower)
values(sysdate,'lipchel','java1');

insert into follow(following_date,following,follower)
values(sysdate,'lipchel','java');

