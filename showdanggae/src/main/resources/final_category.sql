
-- 테이블 생성 순서 : member -> member_category -> main_category ->  product -> item -> 나머지

-- main_category
drop table main_category cascade constraint;
create table main_category (
	category varchar2(100) primary key
);

select * from main_category;


insert into main_category(category) values('notebook');
insert into main_category(category) values('camera');
insert into main_category(category) values('beauty');


-- member_category
drop table member_category cascade constraint;
create table member_category (
	category_id number primary key,
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_category_main foreign key(category) references main_category(category),
	constraint fk_category_member_id foreign key(member_id) references member(member_id)
);
select * from member_category;

create sequence member_category_seq; 

insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'notebook','java');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'beauty','java');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'notebook','dd');
insert into member_category(category_id, category,member_id) values(member_category_seq.nextval, 'notebook','dd');


drop table interest;
create table interest (
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_interest_main foreign key(category) references main_category(category),
	constraint fk_interest_member_id foreign key(member_id) references member(member_id),
	constraint pk_interest primary key (category, member_id)
);

insert into interest(category, member_id) values('notebook','java');
insert into interest(category, member_id) values('beauty','java');
insert into interest(category, member_id) values('notebook','dd');


-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

--참고
alter table board modify hit default 0;
alter table board drop constraint fk_id cascade;

-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB



-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
