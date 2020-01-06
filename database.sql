drop database if exists weatherdb;

create database if not exists weatherdb;

use weatherdb;



create table weather(
datetime_utc date,
conds varchar(25),dewptm int, 
fog int, hail int, hum int, 
precipm int,pressurem int, 
rain int, snow int, 
tempm int, thunder int, 
tornado int, wdire int,
city varchar(20) default 'delhi'
);

select * from weather;

truncate table weather;