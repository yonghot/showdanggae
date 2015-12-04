drop table message;
create table message (
	mno number primary key,
	member_Id varchar2(100),
	message CLOB not null,
	spand_name varchar2(100) not null,
	title varchar2(100) not null,
	spand_date DATE not null,
	read number default 0,
	CONSTRAINT member_Id foreign KEY(member_Id) references member(member_Id)
);

create sequence message_seq;

delete from message;

select * from message;



-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA







-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

member_Id 가 외래키
member_name 은 관리자


notice_seq.nextval

INSERT INTO MESSAGE(member_Id,message,spand_name,title,spand_date,mno)
values ('admin','두번째메세지TEST','관리자','TEST1',SYSDATE,message_seq.nextval);


//특정 사람이 받은 메세지 검색할때
select e.title, m.member_Id, e.message, e.spand_name,e.spand_date
from message e,member m
where e.member_Id=m.member_Id and m.member_Id='seojungspring' order by e.spand_date desc;



update message set read=read+1 where mno=8;
	


-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCccc
	
	
	