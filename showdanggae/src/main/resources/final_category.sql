
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

create table member_category (
	category_id number primary key,
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_category_main foreign key(category) references main_category(category),
	constraint fk_category_member_id foreign key(member_id) references member(member_id)
);

create table interest (
	category varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint fk_interest_main foreign key(category) references main_category(category),
	constraint fk_interest_member_id foreign key(member_id) references member(member_id),
	constraint pk_interest primary key (category, member_id)
);



-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

--참고
alter table board modify hit default 0;
alter table board drop constraint fk_id cascade;



-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB





-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


