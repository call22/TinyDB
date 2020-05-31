create table student1
	(ID			string(5),
	 name			string(20) not null,
	 dept_name		string(20),
	 tot_cred		int,
	 primary key (ID)
	);

create table student2
	(ID			string(5),
	 name			string(20) not null,
	 dept_name		string(20),
	 tot_cred		int,
	 primary key (ID)
	);

insert into student1 values('58595', 'Cronin', 'Physics', 100);
insert into student1 values('72979', 'Guix', 'Astronomy', 117);
insert into student1 values('64140', 'Tiroz', 'Athletics', 118);
insert into student1 values('6523', 'Karlsson', 'Civil Eng.', 1);
insert into student1 values('64067', 'Mennif', 'Languages', 8);
insert into student1 values('99399', 'Duan', 'Astronomy', 96);
insert into student1 values('24746', 'Schrefl', 'History', 4);
insert into student1 values('76672', 'Miliko', 'Statistics', 116);
insert into student1 values('14182', 'Moszkowski', 'Civil Eng.', 73);
insert into student1 values('44985', 'Prieto', 'Biology', 91);
insert into student1 values('35175', 'Quimby', 'History', 4);
insert into student1 values('44271', 'Sowerby', 'English', 108);
insert into student1 values('40897', 'Coppens', 'Math', 58);
insert into student1 values('92839', 'Cirsto', 'Math', 115);
insert into student1 values('79329', 'Velikovs', 'Marketing', 110);
insert into student1 values('24865', 'Tran-', 'Marketing', 116);
insert into student1 values('36052', 'Guerra', 'Elec. Eng.', 59);
insert into student1 values('98940', 'Hawthorne', 'Marketing', 78);
insert into student1 values('21395', 'Leuen', 'Math', 43);
insert into student2 values('30341', 'Anse', 'History', 58);
insert into student2 values('70688', 'Ishihara', 'Elec. Eng.', 86);
insert into student2 values('81258', 'Nirenbu', 'History', 102);
insert into student2 values('63090', 'Hoov', 'Math', 118);
insert into student2 values('70572', 'Andrews', 'Psychology', 7);
insert into student2 values('34404', 'Fries', 'History', 48);
insert into student2 values('59539', 'Madden', 'Civil Eng.', 4);
insert into student2 values('98619', 'Nagaraj', 'Civil Eng.', 61);
insert into student2 values('86753', 'Leister', 'History', 81);
insert into student2 values('87054', 'Dietzsch', 'Statistics', 91);
insert into student2 values('39046', 'Narasimhamu', 'Math', 121);
insert into student2 values('29031', 'Berthold', 'English', 85);
insert into student2 values('97355', 'Ratcliff', 'Languages', 60);
insert into student2 values('24010', 'Brookh', 'Comp. Sci.', 65);
insert into student2 values('21789', 'Bates', 'History', 118);
insert into student2 values('52157', 'Nagle', 'Astronomy', 52);
insert into student2 values('64938', 'Kaep', 'Civil Eng.', 126);
insert into student2 values('94535', 'Nishida', 'History', 127);

select student1.name, student1.tot_cred
    from student1 join student2 on student1.dept_name = student2.dept_name;

select distinct student1.name, student1.tot_cred
    from student1 join student2 on student1.dept_name = student2.dept_name;

select student1.name, student1.tot_cred
    from student1 join student2 on student1.dept_name = student2.dept_name
    where student1.tot_cred > 50;
