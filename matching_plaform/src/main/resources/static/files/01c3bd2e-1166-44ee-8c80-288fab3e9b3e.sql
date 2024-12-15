select dbms_xdb.gethttpport from dual;
select dbms_xdb.getftpport from dual;

exec dbms_xdb.sethttpport(8090);