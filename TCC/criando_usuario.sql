Create tablespace TCC_DAT
datafile 'C:\oraclexe\app\oracle\oradata\XE\tcc_dat.dbf'
size 100m
autoextend on
next 100m
maxsize 2048m;

Create user TCC identified by TCC
default tablespace TCC_DAT;

grant connect, resource, create view to TCC;