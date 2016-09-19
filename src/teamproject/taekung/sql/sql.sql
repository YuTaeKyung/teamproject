CREATE TABLE manager(

  id VARCHAR2(20) CONSTRAINT pk_id PRIMARY KEY ,
  pwd VARCHAR2(20) NOT NULL ,
  email VARCHAR2(50) NOT NULL ,
  phone char(11) NOT NULL ,
  address VARCHAR2(100) NOT NULL ,
  storename VARCHAR2(20) NOT NULL
);
select * from manager;

update manager set email = 'abc@def.com' where email = '123456';
UPDATE manager SEt phone = '01012345678' where id = 'root';

select id from manager where email = '01012345678' and phone = 'abc@def.com';


select id from manager where id = 'asdf';



select id from manager where id ='root';


select id from manager where EMAIL = 'abc@def.com' ;


create TABLE member(
mno NUMBER CONSTRAINT pk_mno PRIMARY KEY ,
name VARCHAR2(10) not null,
phone varchar2(14) not null,
cellphone VARCHAR2(14) not null,
birthdate date not null,
addr varchar2(50) not null,
email VARCHAR2(30) not null
);




UPDATE member set name = '개개개', phone ='99999999' where mno = 1;

select * from member;

insert into member VALUES (1,'개나리','031-962-4893','010-5045-4893','1990-9-20','경기도 고양시', 'taekung92@naver.com');



CREATE TABLE book(
  bno NUMBER CONSTRAINT pk_bno PRIMARY KEY ,
  bname VARCHAR2(30) NOT NULL ,
  genre VARCHAR2(10) NOT NULL ,
  author VARCHAR2(20) NOT NULL ,
  publisher VARCHAR2(20) NOT NULL

);
select * from book;

CREATE table rent (
  rno NUMBER CONSTRAINT pk_rno PRIMARY KEY ,
  mno NUMBER CONSTRAINT fk_mno REFERENCES member(mno),
  bno NUMBER CONSTRAINT fk_bno REFERENCES book(bno),
  regdate date DEFAULT sysdate
);
SELECT bno, (regdate+7)FROM rent;
CREATE SEQUENCE rno;


















