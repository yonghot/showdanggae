
-- 테이블 생성 순서 : member -> member_category -> main_category ->  product -> item -> 나머지

drop table member cascade constraint;
create table member(
	member_id varchar2(100) primary key,
	password varchar2(100) not null,
	member_name varchar2(100) not null,
	email varchar2(100) not null,
	birthday DATE not null,
	report number default 0
);

select * from member;

delete from member;




-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

insert into member (member_Id,password,member_name,email,birthday) values ('java','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) values ('java1','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) values ('java2','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) values ('admin','1234','관리자','admin@showdanggae.com', to_date('120204','RRMMDD')); 



-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB


insert into member values ('dd','tjwjd','������','lipchel@naver.com', to_date('940222','RRMMDD')); 

 update member set email='lipchel@daum.net' , birthday='19880530' where member_Id='javaKING'

 to_date
 
select  to_date(birthday) from member where member_Id='javaKING'; 

to_char(,'yymmdd')


select member_id,to_char(birthday,'RRMMDD') from member where member_Id='javaKING'; 


select member_Id,password,member_name,email,to_date(birthday,'YYMMDD') from member where member_Id='javaKING' 


insert into member values ('asd58e4c','asdf','asdf','asdf',to_char(990823,'RRMMDD'))
insert into member values ('asd58e4c','asdf','asdf','asdf',to_date(990823,'RRMMDD'))

select * from member where member_Id='sdvaeawee'; 

select * from member;
insert into member values ('asd58e4c','asdf','asdf','asdf',to_date(990823,'RRMMDD'))

select member_Id,password,member_name,email,to_date(birthday)as birthday from member where member_Id='avselofias';

 
select member_Id,password,member_name,email,to_char(birthday,'RRMMDD')as birthday from member;
 
 
delete from member where member_Id='java';

 
delete from member where member_Id='javaKING';




-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


