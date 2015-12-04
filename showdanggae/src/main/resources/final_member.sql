
member_Id
password
member_name
email
birthday

drop table member cascade constraint;
create table member(
	member_Id varchar2(100) primary key,
	password varchar2(100) not null,
	member_name varchar2(100) not null,
	email varchar2(100) not null,
	birthday DATE not null,
	report number default 0
)

alter table member add report number not null;

DELETE FROM member;

ALTER TABLE  테이블이름  MODIFY
컬럼에 대해서 데이터 타입이나 크기, 기본값들을 변경할 수 있습니다.
 


 
ALTER TABLE member MODIFY (report number DEFAULT 0);

select * from member;


insert into member (member_Id,password,member_name,email,birthday) values ('zta123','zzzz123','그냥','lipchel@naver.com', 
to_date('940222','RRMMDD')); 
insert into member (member_Id,password,member_name,email,birthday) values ('ztz523','zzzz123','그냥','lipchel@naver.com', 
to_date('940222','RRMMDD')); 
insert into member (member_Id,password,member_name,email,birthday) values ('zzatzz723','zzzz123','그냥','lipchel@naver.com', 
to_date('940222','RRMMDD')); 
insert into member (member_Id,password,member_name,email,birthday) values ('zza2t3','zzzz123','그냥','lipchel@naver.com', 
to_date('940222','RRMMDD')); 
insert into member (member_Id,password,member_name,email,birthday) values ('zt1a23','zzzz123','그냥','lipchel@naver.com', 
to_date('940222','RRMMDD')); 
insert into member (member_Id,password,member_name,email,birthday) values ('zz3a6tz123','zzzz123','그냥','lipchel@naver.com', 
to_date('940222','RRMMDD')); 

insert into member (member_Id,password,member_name,email,birthday) 
values ('java','1234','김용호','blue@blueprint.com', to_date('880307','RRMMDD'));
insert into member (member_Id,password,member_name,email,birthday) 
values ('admin','1234','관리자','admin@showdanggae.com', to_date('120204','RRMMDD')); 
insert into member (member_Id,password,member_name,email,birthday) 
values ('java10','1234','관리자','admin@showdanggae.com', to_date('120204','RRMMDD')); 
insert into member (member_Id,password,member_name,email,birthday) 
values ('java20','1234','관리자','admin@showdanggae.com', to_date('120204','RRMMDD')); 

select * from member where member_Id='seojungspring';

insert into member values ('seojungspring','seojungspring','������','lipchel@naver.com', 
to_date('940222','RRMMDD')); 

insert into member values ('asdf','asdf','������','lipchel@naver.com', 
to_date('940222','RRMMDD')); 


insert into member values ('dd','tjwjd','������','lipchel@naver.com', to_date('940222','RRMMDD')); 

 update member set email='lipchel@daum.net' , birthday='19880530' where member_Id='javaKING'

 to_date
 
select  to_date(birthday) from member where member_Id='javaKING'; 

to_char(,'yymmdd')


/**
 * ���ϴ´�λ̱�
 */


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



-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC --


select member_Id,password,member_name,email,to_char(birthday,'RRMMDD')as birthday from(
select member_Id,password,member_name,email,birthday,ceil(rownum/5) as page from(
select member_Id,password,member_name,email,birthday from member order by member_Id desc)) 
where page=1 and member_Id !='admingalbage' 
								


create table follow(
	following_date DATE not null,
	following varchar2(100) not null,
	follower varchar2(100) not null,
	constraint fk_following foreign key(following) references member(member_id),
	constraint fk_follower foreign key(follower) references member(member_id),
	constraint pk_follow primary key (following,follower)
)
drop table follow
select * from follow
constraint pk_follow primary key (following,follower)
delete from follow

drop table follow
insert into follow(following_da;
insert into follow(following_date,following,follower)
values('2015-01-01','java10','java');
insert into follow(following_date,following,follower)
values('2015-01-01','java10','java');
insert into follow(following_date,following,follower)
values('2015-01-01','java','javaprince');

insert into follow(following_date,following,follower)
values('2015-01-01','test1','java');

select * from follow;
te,following,follower)
values('2015-01-01','javapark','java')
select * from member where member_id='java';


member_Id varchar2(100) not null,
select * from follow;
delete from follow;
select * from member;



