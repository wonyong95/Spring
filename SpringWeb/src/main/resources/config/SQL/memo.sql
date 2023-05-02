drop table memo;
create table memo(
    no number(4) primary key,
    name varchar2(30) not null,
    msg varchar2(100),
    wdate date default sysdate   
);
desc memo;

drop sequence memo_seq;

create sequence memo_seq
start with 1 increment by 1 nocache;