create table palis.dbo.initiative
	(initiative_key int primary key not null identity,
	 initiative_label varchar(50) not null);

insert into palis.dbo.initiative 
(initiative_label) values ('Initiative 1');
insert into palis.dbo.initiative 
(initiative_label) values ('Initiative 3');
insert into palis.dbo.initiative 
(initiative_label) values ('Initiative 4');
