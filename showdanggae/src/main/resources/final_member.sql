
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

delete from member where member_id='david';




-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

insert into member (member_Id,password,member_name,email,birthday) values ('java','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) values ('java1','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) values ('java2','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) values ('java3','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) values ('java4','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) values ('admingalbage','1234','관리자','admin@showdanggae.com', to_date('120204','RRMMDD')); 
<<<<<<< HEAD
insert into member (member_Id,password,member_name,email,birthday) values('david','1234','박성엽','david@naver.com',to_date('850429','RRMMDD'));

insert into member(member_id, password, member_name, email, birthday) values('jaxeva','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member(member_id, password, member_name, email, birthday) values('jaexva1','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member(member_id, password, member_name, email, birthday) values('jaxxvva2','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member(member_id, password, member_name, email, birthday) values('adegalbage','1234','관리자','admin@showdanggae.com', to_date('120204','RRMMDD')); 
insert into member(member_id, password, member_name, email, birthday) values('dewd','dd','dd','asdsdsdin@showdanggae.com', to_date('120204','RRMMDD')); 
insert into member(member_id, password, member_name, email, birthday) values('addr23min','1234','dd','asdsdsdin@showdanggae.com', to_date('120204','RRMMDD')); 

select count(*) from member;
=======
insert into member (member_Id,password,member_name,email,birthday) values('david','1234','박성엽','david_good@naver.com',to_date('850429','RRMMDD'));
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git

-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB


insert into member values ('dd','tjwjd','������','lipchel@naver.com', to_date('940222','RRMMDD')); 

 update member set email='lipchel@daum.net' , birthday='19880530' where member_Id='javaKING'


 
select  to_date(birthday) from member where member_Id='javaKING'; 

to_char(,'yymmdd')


select member_id,to_char(birthday,'RRMMDD') from member where member_Id='javaKING'; 


select member_Id,password,member_name,email,to_date(birthday,'YYMMDD') from member where member_Id='javaKING' 


insert into member values ('asd58e4c','asdf','asdf','asdf',to_char(990823,'RRMMDD'))
insert into member values ('asd58e4c','asdf','asdf','asdf',to_date(990823,'RRMMDD'))

select * from member where member_Id='sdvaeawee'; 

select count(*) from member;
insert into member values ('asd58e4c','asdf','asdf','asdf',to_date(990823,'RRMMDD'))

select member_Id,password,member_name,email,to_date(birthday)as birthday from member where member_Id='avselofias';

 
select member_Id,password,member_name,email,to_char(birthday,'RRMMDD')as birthday from member;
 
 
delete from member where member_Id='java';

 
delete from member where member_Id='javaKING';

insert into member values ('java','1234','박성엽','david@naver.com',to_date(850429,'RRMMDD'));

select member_id,password,member_name,report,email,to_char(birthday,'RRMMDD')as birthday from(
select member_id,password,member_name,report,email,birthday,ceil(rownum/3) as page from(
select member_id,password,member_name,report,email,birthday from member where member_id !='admingalbage' order by member_id desc)) where page=9
	





-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


