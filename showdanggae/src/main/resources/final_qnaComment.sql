
CREATE table QnAcomment(
	cno number primary key,
	no number not null,
	member_name varchar2(100) not null,
	replyComment CLOB not null,
	commentDate DATE not null,
	CONSTRAINT no foreign KEY(no) references qnaboard(no)
);

ALTER TABLE  QnAcomment  RENAME COLUMN reply TO replyComment;

select * from QnAcomment;

create sequence qnacomment_seq;

insert into QnAcomment(cno, no, member_name, replyComment, commentDate) 
values(qnacomment_seq.nextval, 38,'dd','asdase',SYSDATE);



select b.title, b.member_id, b.writeDate,b.,c.cno,c.no,c.replyComment
from qnaboard b,QnAcomment c
where b.no=c.no and b.no=38 and c.no=38;
