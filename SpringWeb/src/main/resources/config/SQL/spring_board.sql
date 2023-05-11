create sequence upCategory_seq NOCACHE; 

insert into upCategory
VALUES(upCategory_seq.nextval,'자동차용품');
commit;
select * from upCategory;

drop table spring_board;
--답변형 게시판
create table spring_board(
    num number(8) primary key,--글번호
    userid varchar2(30) not null, --작성자 아이디
    passwd varchar2(100) not null, --글비밀번호
    subject varchar2(300) not null, --글제목
    content varchar2(4000), --글 내용
    wdate date default sysdate, --작성일
    readnum number(8) default 0, --조회수
    filename varchar2(500), --첨부파일명[uuid_a.txt]=>물리적파일명
    originFIlename varchar2(500), --원본 파일명 [a.txt]=>논리적파일명
    filesize number(8), --첨부파일크기
    
    refer number(8), --글 그룹번호 [답변형 게시판에서 필요한 컬럼]
    lev number(8), --답변 레벨 [답변형 게시판에서 필요한 컬럼]
    sunbun number(8) --같은 글 그룹내에 순서 정렬시 필요한 컬럼 [답변형 게시판에서 필요한컬럼]
);

drop sequence spring_board_seq;

create sequence spring_board_seq nocache;

desc spring_board;