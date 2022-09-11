drop table if exists BOOK;
create table BOOK (
                      id serial not null ,
                      title varchar(150) not null,
                      author varchar(150) not null, description varchar(150), constraint book_pk primary key (id)
);

insert into BOOK (id, title, author, description)
values (1, 'Crime and Punishment', 'F. Dostoevsky', null);
insert into BOOK (id, title, author, description)
values (2, 'Anna Karenina', 'L. Tolstoy', null);

insert into BOOK (id, title, author, description)
values (3, 'The Brothers Karamazov', 'F. Dostoevsky', null);

insert into BOOK (id, title, author, description)
values (4, 'War and Peace', 'L. Tolstoy', null);

insert into BOOK (id, title, author, description)
values (5, 'Dead Souls', 'N. Gogol', null);
commit;
