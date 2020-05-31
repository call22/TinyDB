create table student
	(ID			string(5),
	 name			string(20) not null,
	 dept_name		string(20),
	 tot_cred		int,
	 primary key (ID)
	);

insert into student values ('00128', 'Zhang', 'Comp. Sci.', 102);
insert into student values ('12345', 'Shankar', 'Comp. Sci.', 32);
insert into student values ('19991', 'Brandt', 'History', 80);
insert into student values ('23121', 'Chavez', 'Finance', 110);
insert into student values ('44553', 'Peltier', 'Physics', 56);
insert into student values ('45678', 'Levy', 'Physics', 46);
insert into student values ('54321', 'Williams', 'Comp. Sci.', 54);
insert into student values ('55739', 'Sanchez', 'Music', 38);
insert into student values ('70557', 'Snow', 'Physics', 0);
insert into student values ('76543', 'Brown', 'Comp. Sci.', 58);
insert into student values ('76653', 'Aoi', 'Elec. Eng.', 60);
insert into student values ('98765', 'Bourikas', 'Elec. Eng.', 98);
insert into student values ('98988', 'Tanaka', 'Biology', 120);

delete from student where ID = '00128';
delete from student where tot_cred < 40;
delete from student where name = ID;