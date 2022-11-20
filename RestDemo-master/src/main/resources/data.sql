truncate table student;
truncate table lookup;
insert into student (name, major, grade)
values('David','Computer Science', 99);

insert into student (name, major, grade)
values('Bob','Mathematics', 90);

insert into student (name, major, grade)
values('AJ','Computer Science', 85);

insert into student (name, major, grade)
values('Sara','Computer Science', 92);

insert into student (name, major, grade)
values('Sasha','Mathematics', 95);


insert into lookup (id, type, name, value)
values(1, 'STUDENT_MESSAGE', 'STUDENT_CREATED', 'Successfully Created Student');

insert into lookup (id, type, name, value)
values(2, 'STUDENT_MESSAGE', 'STUDENT_NOT_FOUND', 'Cannot Find Student');

insert into lookup (id, type, name, value)
values(3, 'STUDENT_MESSAGE', 'STUDENT_DELETED', 'Student is Deleted');