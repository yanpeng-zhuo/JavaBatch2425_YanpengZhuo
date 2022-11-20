drop table student;
create table IF NOT EXISTS student
(
   id integer not null auto_increment,
   name varchar(255) not null,
   major varchar(255) not null,
   grade integer not null,
   primary key(id)
);

create table IF NOT EXISTS lookup
(
   id integer not null,
   type varchar(255) not null,
   name varchar(255) not null,
   value varchar(255) not null,
   primary key(id)
);