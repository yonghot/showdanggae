drop table member;
drop table product;

-- main_category
create table main_category (
	category varchar2(100) primary key
);

select * from main_category;

insert into main_category(category) values('노트북');
insert into main_category(category) values('카메라');
insert into main_category(category) values('화장품');


-- member_category
create table member_category (
	category_id number primary key,
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_category_main foreign key(category) references main_category(category),
	constraint fk_category_member_id foreign key(member_id) references member(member_id)
);
select * from member_category;

create sequence member_category_seq; 

insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, '노트북','java');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, '화장품','java');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, '노트북','dd');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, '노트북','dd');

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
	review clob not null,
	review_score number default 0,
	detail clob not null,
	visiblity number default 0,
	regist_date date not null,
	constraint fk_product_category_id foreign key(category_id) references member_category(category_id),
	constraint fk_product_member_id foreign key(member_id) references member(member_id)
);
select * from product;

create sequence product_seq; 

insert into product(product_id, category_id, member_id, product_name, review, detail, visiblity, regist_date)
values(product_seq.nextval, '노트북','dd');


-- seller_link
create table seller_link (
	link varchar2(100) primary key,
	category_id number not null,
	member_id varchar2(100) not null,
	product_id number not null,
	price number not null,
	constraint fk_seller_link_category_id foreign key(category_id) references member_category(category_id),
	constraint fk_seller_link_member_id foreign key(member_id) references member(member_id),
	constraint fk_seller_link_product_id foreign key(product_id) references product(product_id)
);

-- item
create table item (
	item varchar2(100) primary key
);


-- eval_item
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




-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

--참고
alter table board modify hit default 0;
alter table board drop constraint fk_id cascade;



-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB





-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


