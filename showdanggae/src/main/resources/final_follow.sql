
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




-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


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



