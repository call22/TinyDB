create table student
	(ID			string(5),
	 name			string(20) not null,
	 dept_name		string(20),
	 tot_cred		int not null,
	 primary key (ID)
	);
create table instructor
    (
    ID      string(5) primary key ,
    name    string(20) not null,
    dept_name       string(20),
    salary      int,
    primary key (ID)
    );

show databases;
show database TEST;
show table student;
drop table student;
show database TEST;

create database db;
use db;
show databases;
show database db;

create table table1
    (
    ID  int primary key ,
    name string(9),
    now_date    float
    );

create table table2
    (
    ID int primary key ,
    name string(9),
    old_data    long,
    primary key (old_data)
    )

show database db;
show table table1;
show table table2;

show databases;
drop database db;
show databases;