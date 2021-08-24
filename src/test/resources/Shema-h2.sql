create table if not exists client
(
    id bigserial not null
    constraint client
    primary key,
    firstname varchar(20) NOT NULL ,
    lastname varchar(40) NOT NULL ,
    `phone` VARCHAR(15) NOT NULL ,
    `email` VARCHAR(40) NOT NULL ,
    `client _level` VARCHAR(40) NOT NULL ,
    `description` VARCHAR(255) NOT NULL ,
    `order` BIGINT NOT NULL ,
    );