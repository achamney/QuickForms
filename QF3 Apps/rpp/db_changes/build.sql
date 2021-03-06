create database rpp;
GO
USE [rpp]
GO

/****** Object:  Table [dbo].[FACT_visits]    Script Date: 02/25/2014 10:56:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[FACT_visits](
	[visitsKey] [int] IDENTITY(1,1) NOT NULL,
	[deleteFlag] [int] NULL,
	[diagnosisMulti] [int] NULL,
	[service] [int] NULL,
	[location] [int] NULL,
	[notes] [varchar](300) NULL,
	[diagnosis] [varchar](600) NULL,
	[supervising] [int] NULL,
	[age] [int] NULL,
	[gender] [int] NULL,
	[domain] [int] NULL,
	[addedBy] [varchar](50) NULL,
	[seenBeforeFlag] [int] NULL,
	[afterHours] [int] NULL,
	[aboriginalFlag] [int] NULL,
	[disabilityFlag] [int] NULL,
	[homelessFlag] [int] NULL,
	[refugeeFlag] [int] NULL,
	[createdDate] [datetime] NULL,
	[updatedDate] [datetime] NULL,
	[date] [date] NULL,
	[missingAssessment] [int] NULL,
	[assessmentSummary] [varchar](400) NULL,
 CONSTRAINT [PK__visits_f__3297ADFD108B795B] PRIMARY KEY CLUSTERED 
(
	[visitsKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO
USE [rpp]
GO

/****** Object:  Table [dbo].[LKUP_age]    Script Date: 02/25/2014 10:56:51 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[LKUP_age](
	[ageKey] [int] NOT NULL,
	[ageLabel] [varchar](45) NULL,
	[ageOrder] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ageKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[LKUP_age] ADD  DEFAULT ((0)) FOR [ageOrder]
GO
USE [rpp]
GO

/****** Object:  Table [dbo].[LKUP_diagnosis]    Script Date: 02/25/2014 11:03:16 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[LKUP_diagnosis](
	[diagnosisKey] [int] NULL,
	[diagnosisRef] [varchar](200) NULL,
	[diagnosisCategory] [varchar](200) NULL,
	[diagnosisLabel] [varchar](2000) NULL,
	[diagnosisOrder] [varchar](100) NULL,
	[diagnosisDomain] [varchar](200) NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO
USE [rpp]
GO

/****** Object:  Table [dbo].[LKUP_diagnosisMulti]    Script Date: 02/25/2014 11:03:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[LKUP_diagnosisMulti](
	[diagnosisMultiKey] [int] NULL,
	[diagnosisMultiValue] [int] NULL
) ON [PRIMARY]

GO
USE [rpp]
GO

/****** Object:  Table [dbo].[LKUP_gender]    Script Date: 02/25/2014 11:03:38 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[LKUP_gender](
	[genderKey] [int] NOT NULL,
	[genderLabel] [varchar](200) NULL,
	[genderOrder] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[genderKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO
USE [rpp]
GO

/****** Object:  Table [dbo].[LKUP_location]    Script Date: 02/25/2014 11:03:47 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[LKUP_location](
	[locationKey] [int] NOT NULL,
	[locationLabel] [varchar](80) NULL,
	[locationOrder] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[locationKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO
USE [rpp]
GO

/****** Object:  Table [dbo].[LKUP_service]    Script Date: 02/25/2014 11:03:55 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[LKUP_service](
	[serviceKey] [int] NOT NULL,
	[serviceLabel] [varchar](200) NULL,
	[serviceOrder] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[serviceKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO
USE [rpp]
GO

/****** Object:  Table [dbo].[LKUP_supervising]    Script Date: 02/25/2014 11:04:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[LKUP_supervising](
	[supervisingKey] [int] NOT NULL,
	[supervisingLabel] [varchar](45) NULL,
	[supervisingOrder] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[supervisingKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

/****** Object:  Table [dbo].[sql_queries]    Script Date: 02/25/2014 09:50:23 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[sql_queries](
	[queryKey] [int] IDENTITY(1,1) NOT NULL,
	[queryLabel] [varchar](100) NOT NULL,
	[query] [varchar](8000) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[queryKey] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO
create table fact_teamMembers
	(teamMembersKey int primary key not null identity,
	firstName varchar(100),
	lastName varchar(100),
	userName varchar(100),
	password varchar(100),
	email varchar(100),
	userRole int,
	activeFlag int);
GO
create table lkup_userRole
	(userRoleKey int primary key not null,
	userRoleLabel varchar(100),
	userRoleOrder int);
GO
insert into lkup_userRole values (1,'Administrator',0);
insert into lkup_userRole values (2,'User',1);

insert into fact_teamMembers values ('John','Smith','admin','21232f297a57a5a743894a0e4a801fc3','devteam@gmail.com',1,1);
--Username: admin, Password: admin

insert into sql_queries values('getUserByPassword','select * from fact_teamMembers left join lkup_userRole on userRole = userRoleKey %WHERECLAUSE%');
insert into sql_queries values('getTeamMembers','select teamMembersKey as id, ''teamMember'' as form,firstName as "First Name", lastName as "Last Name", userName as "User Name", email as "Email" from fact_teamMembers');
insert into sql_queries values('getFactMetadata','select column_name as field,* from information_schema.columns where table_name like ? order by ordinal_position;');

insert into sql_queries values('getVisitsRows','
select a.visitsKey as id,''newVisit''as form, a.date as "Date", d.ageLabel as "Age", e.genderLabel as "Gender" ,a.diagnosis as "Diagnosis", a.notes as "Notes", f.locationLabel as "Location"
from FACT_visits as a
left join
LKUP_age as d on d.ageKey = a.age
left join
LKUP_gender as e on e.genderKey = a.gender
left join
LKUP_location as f on f.locationKey = a.location
order by visitsKey desc');






