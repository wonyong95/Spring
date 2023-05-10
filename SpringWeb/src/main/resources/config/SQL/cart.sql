--누가 어떤 상품을 몇개 담았는가?
--회원번호 fk
--상품번호 fk
--수량
--날짜
drop table cart;

create table cart(
    cartNum number(8) primary key,
    idx_fk number(8) references member (idx) on delete cascade,
    pnum_fk number(8) references product(pnum) on delete cascade,
    pqty number(8) not null,
    indate date default sysdate,
    constraint cart_pqty_ck check (pqty>0 and pqty<51)
);

drop sequence cart_seq;
create sequence cart_seq nocache;