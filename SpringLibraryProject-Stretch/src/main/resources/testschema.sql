DROP TABLE IF EXISTS book;
CREATE TABLE book (id bigint not null auto_increment, author varchar(255), title varchar(255), total_pages integer not null, primary key (id));