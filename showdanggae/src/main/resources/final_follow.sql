
drop table follow;
create table follow(
	following_date DATE not null,
	following varchar2(100) not null,
	follower varchar2(100) not null,
	constraint fk_following foreign key(following) references member(member_id),
	constraint fk_follower foreign key(follower) references member(member_id),
	constraint pk_follow primary key (following,follower)
);


drop table follow;
select * from follow;
delete from follow;


-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA



-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
select from follow
where follower
following_date
select * from follow;

following  // follower
a // a를 받아보는사람

insert into follow(following_date,following,follower)
values(sysdate,'java','java2');

insert into follow(following_date,following,follower)
values(sysdate,'lipchel','dd');

insert into follow(following_date,following,follower)
values(sysdate,'java2','java');

select follower from follow where following='lipchel';

SELECT CURRENT_DATE FROM DUAL;	
SELECT SYSDATE -1 FROM DUAL;

select * from follow;

select *
from follow 
where following_date between to_date('2015-12-11', 'YYYY-MM-DD') and to_date('2015-12-14', 'YYYY-MM-DD')
and following='lipchel';

/* 어제 */ 날짜칼럼 BETWEEN TRUNC(SYSDATE-1) AND TRUNC(SYSDATE-1)+0.99999421
/* 오늘 */ 날짜칼럼 BETWEEN TRUNC(SYSDATE) AND TRUNC(SYSDATE) + 0.99999421

select follower,following_date
from(select * from follow where following_date  BETWEEN TRUNC(SYSDATE) AND TRUNC(SYSDATE) + 0.99999421)
where following='lipchel';
//lipchel을 오늘 팔로잉한사람

-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


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





