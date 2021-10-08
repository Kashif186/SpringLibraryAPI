drop table if exists book;
drop table if exists person;

create table book (id bigint not null auto_increment, author varchar(255), title varchar(255), total_pages integer, person_id bigint, primary key (id));

create table person (id bigint not null auto_increment, fine double precision, first_name varchar(255), last_name varchar(255), phone_number integer, primary key (id)) ;

alter table book add constraint person foreign key (person_id) references person (id);


