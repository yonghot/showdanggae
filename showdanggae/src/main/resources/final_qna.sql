
-- 테이블 생성 순서 : member -> member_category -> main_category ->  product -> item -> 나머지


drop table qnaboard cascade constraint;

create table qnaboard(
	no number primary key,
	title varchar2(100) not null,
	member_id varchar2(100) not null,
	writer varchar2(100) not null,
	content CLOB not null,
	writeDate DATE not null,
	viewCount number default 0,
	ref number not null, 
	restep number not null, 
	relevel number not null,
	CONSTRAINT member_id foreign KEY(member_id) references member(member_id)
);


insert into qnaboard(no, title, member_id, writer, content, writeDate,viewCount,ref,restep,relevel) 
values(qna_seq.nextval, '질문입니다','java','김용호', '언제 개발 완료 되는거죠?', SYSDATE,0,qna_seq.nextval,0,0);

insert into noticeboard (no,title,member_id,writer,content,writeDate,viewCount,ref,restep,relevel) 
values(#{no},#{title},#{member_id},#{writer},#{content},SYSDATE,0,#{no},0,0)

insert into qnaboard(no, title, member_id, writer, content, writeDate,viewCount,ref,restep,relevel) 
values(qna_seq.nextval, '질문입니다','java','김용호', '언제 개발 완료 되는거죠?', SYSDATE,0,qna_seq.nextval,0,0);


select * from qnaboard;

SELECT qna_seq.nextval from dual
create sequence qna_seq;

	ref number not null, -- 답변글묶음 번호, 원게시글 번호 
	restep number not null, -- 답변글묶음내 글순서(정렬 오름차순)
	relevel number not null -- 답변글레벨 , 답변의 단계 

			SELECT no,title,member_id,writer,content,writeDate,viewCount,ref, restep,relevel
					    FROM(	 
								SELECT no,title,member_id,writer,content,writeDate,viewCount,
								CEIL(rownum/5) AS page,ref,restep,relevel   
								FROM ( 
									SELECT no,title,member_id,writer,content,viewCount,
									to_char(writeDate,'YYYY.MM.DD') as writeDate,
									ref,restep,relevel 
									FROM qnaboard
									order by ref desc,restep asc  
									 ) 
    ) WHERE PAGE=#{value}