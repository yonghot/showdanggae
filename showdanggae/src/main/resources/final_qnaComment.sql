
CREATE table QnAcomment(
	cno number primary key,
	no number not null,
	member_name varchar2(100) not null,
	member_id VARCHAR2(100) not null,
	replyComment CLOB not null,
	commentDate DATE not null,
	CONSTRAINT no foreign KEY(no) references qnaboard(no),
	CONSTRAINT FK_QnAcomment FOREIGN KEY(member_id) references member(member_id)
);

ALTER TABLE QnAcomment
ADD (member_id VARCHAR2(100));


ALTER TABLE QnAcomment
ADD CONSTRAINT FK_QnAcomment FOREIGN KEY(member_id)
references member(member_id);


ALTER TABLE  QnAcomment  RENAME COLUMN reply TO replyComment;

select * from QnAcomment;

COUNT(*) AS total

select no,member_name,replyComment,to_char(commentDate,'YYYY.MM.DD') as commentDate
from QnAcomment 
where no=38
order by cno desc;


select publisher,avg(price) as 평균가 from book group by publisher order by 평균가 desc;



to_char(commentDate,'YYYY.MM.DD') as commentDate


create sequence qnacomment_seq;

insert into QnAcomment(cno, no, member_name, replyComment, commentDate) 
values(qnacomment_seq.nextval, 38,'dd','두번째댓글',SYSDATE);

insert into QnAcomment(cno, no, member_name, replyComment, commentDate) 
values(qnacomment_seq.nextval, 41,'dd','두번째댓글',SYSDATE);


select b.no,b.title, b.member_id, b.writeDate,b.viewCount,b.content,b.ref,b.restep,b.relevel,
		c.cno,c.no,c.replyComment,c.member_name
from qnaboard b,QnAcomment c
where b.no=c.no and b.no=38 and c.no=38;




    
    	select no,title,member_id,writer,content,viewCount,
		to_char(writeDate,'YYYY.MM.DD') as writeDate,
		ref,restep,relevel 
		from qnaboard
		where no=38;
		
		
		
select b.no,b.title, b.member_id, to_char(b.writeDate,'YYYY.MM.DD') as writeDate,b.viewCount,b.content,b.ref,b.restep,b.relevel,
c.cno,c.no,c.replyComment,c.member_name
from qnaboard b,QnAcomment c
where b.no=c.no and b.no=38 and c.no=38;




SELECT no, title, member_id, writer, content, writeDate, viewCount, ref, restep, relevel
	FROM( SELECT no,title,member_id,writer,content,writeDate,viewCount,ceil(rownum/5) AS page,ref,restep,relevel
		FROM( SELECT no,title,member_id,writer,content,viewCount,to_char(writeDate,'YYYY.MM.DD') as writeDate,ref,restep,relevel
		
			from qnaboard qb, QnAcomment qc order by qb.no desc) 
  ) WHERE page=1;
  
 select count(cno) as total
 from qnaboard qb, QnAcomment qc
 where qc.no = qb.no;
 
select no from QnAcomment group by no;
select * from qnaboard;



    select cno,no,member_name,replyComment,to_char(commentDate,'YYYY.MM.DD') as commentDate,member_id
from QnAcomment where no=47 order by cno asc



insert into QnAcomment(cno,no,member_name,replyComment,commentDate,member_id) 
values(qnacomment_seq.nextval, 47,'dd','두번째댓글',SYSDATE,'dd');



delete from QnAcomment where cno=84;


