
-- 테이블 생성 순서 : member -> member_category -> main_category ->  product -> item -> 나머지

-- product
drop table product cascade constraint; -- 제약조건 있는 테이블은 이렇게 삭제...하면 제약조건들이 없어져서 되는거다
create table product (
	product_id number primary key,
	category_id number not null,
	member_id varchar2(100) not null,
	product_name varchar2(100) not null,
	likes number default 0,
	dislikes number default 0,
	hits number default 0,
	review clob,
	review_score number default 0,
	detail clob,
	visiblity number default 0,
	regist_date date not null,
	constraint fk_product_category_id foreign key(category_id) references member_category(category_id),
	constraint fk_product_member_id foreign key(member_id) references member(member_id)
);
select * from product;

select category_id from product;

create sequence product_seq; 

insert into product(product_id, category_id, member_id, product_name, review, detail, visiblity, regist_date)
values(product_seq.nextval, '노트북','dd');

-- item
create table item (
	item varchar2(100) primary key
);



-- seller_link
drop table seller_link cascade constraint;
create table seller_link (
	link varchar2(300) not null,
	product_id number not null,
	price number not null,
	constraint fk_seller_link_product_id foreign key(product_id) references product(product_id),
	constraint pk_seller_link primary key (link, product_id)
);

select * from SELLER_LINK;


-- eval_item
drop table eval_item cascade constraint;
create table eval_item (
	item varchar2(100) not null,
	product_id number not null,
	item_point number default 0,
	constraint fk_eval_item_item foreign key(item) references item(item),
	constraint fk_eval_itemt_product_id foreign key(product_id) references product(product_id),
	constraint pk_eval_item primary key (item, product_id)
);

select * from eval_item;


-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

--참고
alter table board modify hit default 0;
alter table board drop constraint fk_id cascade;



내가 지금까지 만든 테이블 모두 조회
select * from user_tables; 
테이블 삭제(자식테이블까지 모두 삭제)
drop table main_category CASCADE constraints;
컬럼(카테고리)추가
alter table main_category add(beauty varchar2(100), computer varchar2(100), 
mobile varchar2(100), camera varchar2(100), book varchar2(100));
**************************************************
**************************************************

관심사 테이블 생성
create table interest(
	member_id varchar2(100) not null,
	category varchar2(100) not null,
	constraint fk_interest_member_id foreign key(member_id) references member(member_id),
	constraint fk_interest_category foreign key(category) references main_category(category)
);
관심사 데이터 입력
insert into interest(member_id, category) values('java', 'computer');
insert into interest(member_id, category) values('java', 'book');
insert into interest(member_id, category) values('java1', 'computer');
insert into interest(member_id, category) values('java1', 'computer');
insert into interest(member_id, category) values('lipchel', '화장품');
insert into interest(member_id, category) values('lipchel', '노트북');
테이블 조회
select * from interest where member_id='lipchel';
select  DISTINCT category from interest;
멤버삭제(컬럼삭제)
delete from interest where member_id='java';
**************************************************

멤버테이블
create table member (
	member_id varchar2(100) primary key,
	password varchar2(100) not null,
	member_name varchar2(100) not null,
	email varchar2(100) not null,
	birthday date not null
);
멤버 데이터 삽입
insert into member(member_Id,password,member_name,email,birthday) 
values('java', '1234', '강민석', 'aa@aa.com', to_date(830330,'RRMMDD'));
insert into member(member_Id,password,member_name,email,birthday) 
values('java1', '1234', '김용호', 'aa@aa.com', to_date(880110,'RRMMDD'));
insert into member(member_Id,password,member_name,email,birthday) 
values('java3', '1234', '유서정', 'aa@aa.com', to_date(930110,'RRMMDD'));

멤버 테이블 조회
select * from member;
**************************************************

메인_카테고리 테이블
create table main_category (
	category varchar2(100) primary key
);
시퀀스
create sequence main_category_seq;
테스트 데이터 삽입
insert into main_category(category) values('beauty');
insert into main_category(category) values('computer');
insert into main_category(category) values('mobile');
insert into main_category(category) values('camera');
insert into main_category(category) values('book');
메인_카테고리 테이블 조회
select * from main_category;
****************************************************

멤버_카테고리 테이블 생성
create table member_category (
	category_id number primary key,
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_category_main foreign key(category) references main_category(category),
	constraint fk_category_member_id foreign key(member_id) references member(member_id)
);
시퀀스 생성
create sequence member_category_seq; 
시퀀스 삭제
drop sequence member_category_seq;
멤버_카테고리 데이터 삽입(시퀀스 삽입 포함)
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'computer','java');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'camera','java');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'computer','java1');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'clothes','java1');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'book','java');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'clothes','java3');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'beauty','java3');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'clothes','java2');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'beauty','java1');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'mobile','java3');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'beauty','java4');

멤버_카테고리 테이블 조회
select * from member_category;
컬럼삭제
delete from member_category where member_id='java';
delete from member_category where member_id='java2';
row삭제
delete from member_category where category_id='31'; 
****************************************************

상품테이블 생성
create table product (
	product_id number primary key,
	category_id number not null,
	member_id varchar2(100) not null,
	product_name varchar2(100) not null,
	likes number default 0,
	dislikes number default 0,
	review clob not null,
	review_score number default 0,
	detail clob not null,
	visiblity number default 0,
	regist_date date not null,
	constraint fk_product_category_id foreign key(category_id) references member_category(category_id),
	constraint fk_product_member_id foreign key(member_id) references member(member_id)
);
시퀀스 생성
시퀀스 생성
create sequence product_id_seq; 
시퀀스 삭제
drop sequence product_id_seq;
데이터 삽입
insert into product(product_id, category_id, member_id, product_name, 
likes, dislikes, review, review_score, detail, visiblity, regist_date) 
values(product_id_seq.nextval, 1,'java', '서피스북', 
100, 1, '좋네요~', 70, '가격꽝', 90, to_date(sysdate,'yyyy,mm,dd hh24:mi:ss'));


**
create table seller_link (
	link varchar2(100) not null,
	product_id number not null,
	price number not null,
	constraint fk_seller_link_product_id foreign key(product_id) references product(product_id),
	constraint pk_seller_link primary key (link, product_id)
);



create table item (
	item varchar2(100) primary key
);
//항목별 평가 하는 테이블
create table eval_item (
	item varchar2(100) primary key,
	category_id number not null,
	member_id varchar2(100) not null,
	product_id number not null,
	item_point number default 0,
	constraint fk_eval_item_item foreign key(item) references item(item),
	constraint fk_eval_item_category_id foreign key(category_id) references member_category(category_id),
	constraint fk_eval_item_member_id foreign key(member_id) references member(member_id),
	constraint fk_eval_itemt_product_id foreign key(product_id) references product(product_id)
);

alter table board modify hit default 0;

alter table board drop constraint fk_id cascade;



select category_id, member_id, category 
		from member_category
		where member_id='java';
		
		insert into interest(category, member_id) 
		values('카메라','java');
		
		
		



-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB





-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


