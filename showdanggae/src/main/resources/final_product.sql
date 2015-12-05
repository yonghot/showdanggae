
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

-- item
create table item (
	item varchar2(100) primary key
);

<<<<<<< HEAD
=======


-- seller_link
drop table seller_link;
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
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


-- eval_item
drop table eval_item cascade constraint;
create table eval_item (
	item varchar2(100) primary key,
	product_id number not null,
	item_point number default 0,
	constraint fk_eval_item_item foreign key(item) references item(item),
	constraint fk_eval_itemt_product_id foreign key(product_id) references product(product_id)
);




-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

--참고
alter table board modify hit default 0;
alter table board drop constraint fk_id cascade;



-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB





-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


