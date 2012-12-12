SET DEFINE OFF;

--建立之前需要先删除
drop tablespace finance including contents and datafiles;
drop user root;

--建立表空间
create tablespace finance logging datafile 'E:\oracle\product\10.2.0\oradata\finance.dat' size 10m;
create user root identified by root default tablespace finance;
grant connect,resource to root;

COMMIT;