
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
here member_id !='admingalbage'

//삭제할때
 delete from interest where member_id='lipchel' and category='화장품';
//내 관심사 제외한 카테고리
 select category from main_category where category not in (select  category from interest where member_id='lipchel')

 //모든 등록할 수 있는 카테고리
 select category from main_category

select  category from interest where NOT 
노트북 화장품
DISTINCT
select * from interest;
-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
