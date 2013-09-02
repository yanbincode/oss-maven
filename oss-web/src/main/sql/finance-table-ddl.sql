SET DEFINE OFF;

--����pay_info��
create table pay_info (
  record_id number not null,  --����,����
  day_time date not null,
  pay_content varchar2(2000),  --֧��ȥ��
  pay_number number,  --֧�����
  remark varchar2(2000),
  pay_member_id number not null,   --��� ������Ա��
  pay_type number not null,
  pay_value varchar2(2),
  creator_id  number not null ,
  created_time date not null ,
  modifier_id number not null, 
  modified_time date not null
)
tablespace finance;

--����������
create sequence s_pay_info
increment by 1
start with 1;

alter table pay_info
add constraint pk_pay_info primary key(record_id);

--����earn_info ��
create table earn_info (
  record_id number not null,  --����,����
  day_time date,
  earn_content varchar2(2000),  --����ȥ��
  earn_number number,  --������
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

--����day_record��
create table day_record (
  record_id number not null,  --����,����
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

--����month_record��
create table month_record (
  record_id number,  --����,����
  month_time date,
  month_earn_count number,  --������
  month_pay_count number,  --֧�����
  month_count number, --���ܼ�
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

--����year_record��
create table year_record (
  record_id number,  --����,����  
  year_time date,
  year_earn_count number,  --������
  year_pay_count number,  --֧�����
  year_count number, --���ܼ�
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

--ͳ�����ͱ�
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

--����member��
create table member_info (
  member_id number,  --����,����
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

--����Ա��sequence
create sequence s_member_info
increment by 1
start with 1;

alter table member_info
add constraint pk_member primary key(member_id);

--��Դ
create table oss_resource (
       resource_id number,
       resource_parent_id number,
       name varchar2(255),
       type varchar2(2),
       active varchar2(2),
       res_interface varchar2(4000),
       description varchar2(4000),
       creator_id number,
       created_time date,
       modifier_id number,
       modified_time date
)
tablespace finance;

alter table oss_resource
add constraint pk_oss_resource primary key(resource_id);

create sequence s_oss_resource
increment by 1
start with 1;

--��ɫ
create table oss_role (
       role_id number,
       name varchar2(255),
       role_level varchar2(2),
       active varchar2(2),
       description varchar2(4000),
       creator_id number,
       created_time date,
       modifier_id number,
       modified_time date
)
tablespace finance;

alter table oss_role
add constraint pk_oss_role primary key(role_id);

create sequence s_oss_role
increment by 1
start with 1;

--��Ա��ɫ��
create table oss_member_role (
       record_id number,
       member_id number,
       role_id number
)
tablespace finance;

alter table oss_member_role
add constraint pk_oss_member_role primary key(record_id);

create sequence s_oss_member_role
increment by 1
start with 1;

--��Ա����Դid���Ψһ������

--��ɫ��Դ��
create table oss_role_resource (
       record_id number,
       role_id number,
       resource_id number
)
tablespace finance;

alter table oss_role_resource
add constraint pk_oss_role_resource primary key(record_id);

create sequence s_oss_role_resource
increment by 1
start with 1;

--��Դ�ͽ�ɫid���Ψһ������

commit;
