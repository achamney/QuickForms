create table palis.dbo.area
	(area_key int primary key not null identity,
	 area_label varchar(50) not null);

insert into palis.dbo.area 
(area_label) values ('Ottawa');

insert into palis.dbo.area 
(area_label) values ('Renfrew');

insert into palis.dbo.area 
(area_label) values ('Eastern Counties');
