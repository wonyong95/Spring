create sequence upCategory_seq NOCACHE; 

insert into upCategory
VALUES(upCategory_seq.nextval,'�ڵ�����ǰ');
commit;
select * from upCategory;

drop table spring_board;
--�亯�� �Խ���
create table spring_board(
    num number(8) primary key,--�۹�ȣ
    userid varchar2(30) not null, --�ۼ��� ���̵�
    passwd varchar2(100) not null, --�ۺ�й�ȣ
    subject varchar2(300) not null, --������
    content varchar2(4000), --�� ����
    wdate date default sysdate, --�ۼ���
    readnum number(8) default 0, --��ȸ��
    filename varchar2(500), --÷�����ϸ�[uuid_a.txt]=>���������ϸ�
    originFIlename varchar2(500), --���� ���ϸ� [a.txt]=>�������ϸ�
    filesize number(8), --÷������ũ��
    
    refer number(8), --�� �׷��ȣ [�亯�� �Խ��ǿ��� �ʿ��� �÷�]
    lev number(8), --�亯 ���� [�亯�� �Խ��ǿ��� �ʿ��� �÷�]
    sunbun number(8) --���� �� �׷쳻�� ���� ���Ľ� �ʿ��� �÷� [�亯�� �Խ��ǿ��� �ʿ����÷�]
);

drop sequence spring_board_seq;

create sequence spring_board_seq nocache;

desc spring_board;