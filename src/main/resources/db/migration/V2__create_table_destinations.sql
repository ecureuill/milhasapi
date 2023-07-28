create table destinations(
    id bigint not null auto_increment,
    name varchar(100) not null,
    photo varchar(100) not null,
    price decimal not null,
    primary key(id)
);