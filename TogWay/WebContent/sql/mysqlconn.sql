show databases;


--테이블 생성	
	create table member(
	name varchar(20) not null,
	userid varchar(50) not null primary key,
	passwd varchar(16) not null,
	email varchar(50) not null,
	tel varchar(20) not null,
	reg_date timestamp not null default current_timestamp
);
	create table comment(
	num int not null primary key auto_increment,
	writer varchar(50) not null,
	content text not null,
	passwd varchar(60) not null,
	reg_date timestamp not null default current_timestamp,
	ref int not null
);
	
	
	
	c
	
create table board(
	num int not null primary key auto_increment,
	writer varchar(50) not null,
	subject varchar(50) not null,
	content text not null,
	passwd varchar(60) not null,
	reg_date timestamp not null default current_timestamp,
	readcount int default 0,
	ref int not null,
	re_step smallint not null,
	re_level smallint not null,
	div_num int not null
);

select * from board
delete board
drop table board










create table review(
	num int not null primary key auto_increment,
	writer varchar(50) not null,
	subject varchar(50) not null,
	content text not null,
	passwd varchar(60) not null,
	reg_date timestamp not null default current_timestamp,
	readcount int default 0,
	ref int not null,
	re_step smallint not null,
	re_level smallint not null,
	div_num int not null
);


create table hospital(
	num int not null primary key auto_increment,
	writer varchar(50) not null,
	subject varchar(50) not null,
	content text not null,
	passwd varchar(60) not null,
	reg_date timestamp not null default current_timestamp,
	readcount int default 0,
	ref int not null,
	re_step smallint not null,
	re_level smallint not null,
	div_num int not null
);

create table fleamarket(
	num int not null primary key auto_increment,
	writer varchar(50) not null,
	subject varchar(50) not null,
	content text not null,
	passwd varchar(60) not null,
	reg_date timestamp not null default current_timestamp,
	readcount int default 0,
	ref int not null,
	re_step smallint not null,
	re_level smallint not null,
	div_num int not null
);

























show tables;


	ALTER TABLE board DROP re_lavel;
	
	alter table board convert to charset utf8;



	
	






delete from board where num = 1;




--테이블 보기
desc member;
desc mypet;

-- 테이블 삭제

drop table board;	
select * from board;		
select * from member;		
drop table member;
select * from member;


select * from board where subject like '%2%' order by ref desc, re_step asc limit 1,2;
select count(*) from board where subject like'%2%';
select * from board where subject like '%2%' 

