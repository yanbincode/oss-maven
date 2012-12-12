SET DEFINE OFF;

--建立pay_info表
create table pay_info (
  record_id number not null,  --自增,主键
  day_time date not null,
  pay_content varchar2(2000),  --支出去向
  pay_number number,  --支出金额
  remark varchar2(2000),
  pay_member_id number not null,   --外键 关联成员表
  pay_type number not null,
  pay_value varchar2(2),
  creator_id  number not null ,
  created_time date not null ,
  modifier_id number not null, 
  modified_time date not null
)
tablespace finance;

--建自增序列
create sequence s_pay_info
increment by 1
start with 1;

alter table pay_info
add constraint pk_pay_info primary key(record_id);

--建立earn_info 表
create table earn_info (
  record_id number not null,  --自增,主键
  day_time date,
  earn_content varchar2(2000),  --收入去向
  earn_number number,  --收入金额
  remark varchar2(2000),
  earn_member_id number,
  earn_type number not null,
  creator_id  number not null ,
  created_time date not null ,
  modifier_id number not null, 
  modified_time date not null
)
tablespace finance;

create sequence s_earn_info
increment by 1
start with 1;

alter table earn_info
add constraint pk_earn_info primary key(record_id); 

--建立day_record表
create table day_record (
  record_id number not null,  --自增,主键
  day_time date,
  day_pay_count number,  
  day_earn_count number,
  day_count number,
  remark varchar2(2000),
  creator_id number not null ,
  created_time date not null ,
  modifier_id number not null, 
  modified_time date not null
)
tablespace finance;

create sequence s_day_record
increment by 1
start with 1;

alter table day_record
add constraint pk_day_record primary key(record_id);  

--建立month_record表
create table month_record (
  record_id number,  --自增,主键
  month_time date,
  month_earn_count number,  --收入金额
  month_pay_count number,  --支出金额
  month_count number, --月总计
  remark varchar2(2000),
  creator_id  number not null,
  created_time date not null,
  modifier_id number not null, 
  modified_time date not null
)
tablespace finance;

create sequence s_month_record
increment by 1
start with 1;

alter table month_record
add constraint pk_month_record primary key(record_id); 

--建立year_record表
create table year_record (
  record_id number,  --自增,主键  
  year_time date,
  year_earn_count number,  --收入金额
  year_pay_count number,  --支出金额
  year_count number, --年总计
  remark varchar2(2000),
  creator_id  number not null ,
  created_time date not null ,
  modifier_id number not null, 
  modified_time date not null
)
tablespace finance;

alter table year_record
add constraint pk_year_record primary key(record_id); 

create sequence s_year_record
increment by 1
start with 1;

--建立member表
create table member_info (
  member_id number,  --自增,主键
  name varchar(50),
  age number,
  gender number,
  phone number,
  id_card number,
  user_name varchar(50) unique,
  pass_word varchar(50),
  creator_id number not null ,
  created_time date not null ,
  modifier_id number not null, 
  modified_time date not null
)
tablespace finance;

--给成员表建sequence
create sequence s_member_info
increment by 1
start with 1;

alter table member_info
add constraint pk_member primary key(member_id);

--统计类型表
create table account_type (
       record_id number,
       type_id varchar2(2),
       type_name varchar2(255),
       use_place varchar2(10),
       active varchar2(2),
       type_description varchar2(4000),
       creator_id number,
       created_time date,
       modifier_id number,
       modified_time date
)
tablespace finance;

alter table account_type
add constraint pk_account_type primary key(record_id);

create sequence s_account_type
increment by 1
start with 1;

commit;