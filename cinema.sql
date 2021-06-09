drop database IF exists movie;
create database movie;
use movie;

CREATE TABLE movielist (
    movienum int NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    price int not null,
    PRIMARY KEY (movienum)
);
CREATE TABLE members (
    name varchar(10) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    age int not null,
    PRIMARY KEY (phone)
);

CREATE TABLE orders (
   ordernum int not null auto_increment,
   phone varchar(20) NOT NULL,
   movienum int NOT NULL,
   time int not null,
   seat int not null check (seat<=50),
   PRIMARY KEY (ordernum),
   FOREIGN KEY (movienum) REFERENCES movielist(movienum),
   FOREIGN KEY (phone) REFERENCES members(phone)
);

insert into members values ("원조","010-2222-3333","18");
insert into members values ("동기","010-3333-3333","20");
insert into movielist(name,price) values ("겨울왕국",20000);
insert into movielist(name,price) values ("겨울왕국2",30000);
insert into movielist(name,price) values ("겨울왕국3",30000);
insert into movielist(name,price) values ("겨울왕국4",30000);

insert into orders(phone,movienum,time,seat) values ("010-3333-3333","1","2",5);
insert into orders(phone,movienum,time,seat) values ("010-3333-3333","1","2",6);
insert into orders(phone,movienum,time,seat) values ("010-3333-3333","1","2",1);


