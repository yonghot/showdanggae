drop table noticeboard;
create table noticeboard(
	no number primary key,
	TITLE varchar2(100) not null,
	writer varchar2(100) not null,
	content CLOB not null,
	hit number default 0,
	writeDate DATE not null
);

create sequence notice_seq;


-- AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA




-- BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

insert into noticeboard(no,TITLE,writer,password,content,writeDate) 
values(notice_seq.nextval,'Z','관리자','1234','방가방가',SYSDATE);
insert into noticeboard(no,TITLE,writer,password,content,writeDate) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);
insert into noticeboard(no,TITLE,writer,password,content,writeDate) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);
insert into noticeboard(no,TITLE,writer,password,content,writeDate) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);
insert into noticeboard(no,TITLE,writer,password,content,writeDate) 
values(notice_seq.nextval,'공지사항입니다','관리자','1234','방가방가',SYSDATE);



update noticeboard set title='asdfasdf' , content='asdfasdf' where no=30
	
select count(*) from noticeboard;
	
	
	
select no,TITLE,writer,password,content,hit,time_post from(
select no,TITLE,writer,password,content,hit,time_post,ceil(rownum/10) as page from(
select no,TITLE,writer,password,content,hit,time_post from noticeboard order by no desc)) where page=1;
	

삭제 

delete from noticeboard where no=37 or no=8

서브쿼리
delete from noticeboard
where no=(5);


DELETE FROM noticeboard WHERE no=(SELECT no FROM noticeboardWHERE no=22,no=8)

select no,TITLE,writer,password,content,hit,to_char(time_post,'YYYY-MM-DD') from(
select no,TITLE,writer,password,content,hit,time_post,ceil(rownum/5) as page from(
select no,TITLE,writer,password,content,hit,time_post from noticeboard order by no desc)) where page=3;




-- CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC




